package roy.application.master.netty;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;

public final class CallNettyObservable<T> extends Observable<NettyResponse<T>> {

    private final CallNetty<T> mTCallNetty;

    public CallNettyObservable(CallNetty<T> originalCall){
        this.mTCallNetty = originalCall;
    }

    @Override
    protected void subscribeActual(Observer<? super NettyResponse<T>> observer) {

        CallNetty<T> callNetty = mTCallNetty.clone();

        observer.onSubscribe(new CallNettyDisposable(callNetty, observer));

        callNetty.execute();
    }

    private static final class CallNettyDisposable<T> implements Disposable,CallBack<T>{

        private final CallNetty<?> mCallNetty;
        private final Observer<? super NettyResponse<T>> mObserver;

        CallNettyDisposable(CallNetty<?> callNetty,Observer<? super NettyResponse<T>> observer){
            this.mCallNetty = callNetty;
            this.mObserver  = observer;
        }


        @Override
        public void dispose() {
            mCallNetty.cancel();
        }

        @Override
        public boolean isDisposed() {
            return mCallNetty.isCanceled();
        }

        @Override
        public void onRequest(NettyRequest nettyRequest) {

        }

        @Override
        public void onResponse(NettyResponse<T> nettyResponse) {
            boolean terminated = false;
            try{
                if(!mCallNetty.isCanceled()){
                    mObserver.onNext(nettyResponse);
                }

                if(!mCallNetty.isCanceled()){
                    mObserver.onComplete();
                }

            }catch (Throwable throwable){
                if(terminated){
                    RxJavaPlugins.onError(throwable);
                }else if(!mCallNetty.isCanceled()){
                    mObserver.onError(throwable);
                }
            }
        }

        @Override
        public void onFailure(Throwable errorMessage) {
            mObserver.onError(errorMessage);
        }

        @Override
        public void onConnectState(boolean state) {

        }
    }
}
