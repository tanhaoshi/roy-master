package roy.application.master.netty;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;


//extends CallNettyDisposableDispatcher
public final class CallNettyDisposable<T> {

//    private final CallNetty<?> mCallNetty;
//    private final Observer<? super NettyResponse<T>> mObserver;
//
//    CallNettyDisposable(CallNetty<?> callNetty,Observer<? super NettyResponse<T>> observer){
//        this.mCallNetty = callNetty;
//        this.mObserver  = observer;
//    }
//
//    @Override
//    public void dispose() {
//        mCallNetty.cancel();
//    }
//
//    @Override
//    public boolean isDisposed() {
//        return mCallNetty.isCanceled();
//    }
//
//    @Override
//    public void onRequest(NettyRequest nettyRequest) {
//
//    }
//
//    @Override
//    public void onResponse(NettyResponse nettyResponse) {
//        boolean terminated = false;
//        try{
//            if(!mCallNetty.isCanceled()){
//                mObserver.onNext(nettyResponse);
//            }
//
//            if(!mCallNetty.isCanceled()){
//                mObserver.onComplete();
//            }
//
//        }catch (Throwable throwable){
//            if(terminated){
//                RxJavaPlugins.onError(throwable);
//            }else if(!mCallNetty.isCanceled()){
//                mObserver.onError(throwable);
//            }
//        }
//    }
//
//    @Override
//    public void onFailure(Throwable errorMessage) {
//        mObserver.onError(errorMessage);
//    }
//
//    @Override
//    public void onConnectState(boolean state) {
//
//    }

}
