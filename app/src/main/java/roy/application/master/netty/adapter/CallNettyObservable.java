package roy.application.master.netty.adapter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

final class CallNettyObservable<T> extends Observable<Response<T>> {

    private final CallNetty<T> mTCallNetty;

    CallNettyObservable(CallNetty<T> originalCall){
        this.mTCallNetty = originalCall;
    }

    @Override
    protected void subscribeActual(Observer<? super Response<T>> observer) {

    }

    private static final class CallNettyDisposable implements Disposable{

        private final CallNetty<?> mCallNetty;

        CallNettyDisposable(CallNetty<?> callNetty){
            this.mCallNetty = callNetty;
        }

        @Override
        public void dispose() {
            mCallNetty.cancel();
        }

        @Override
        public boolean isDisposed() {
            return mCallNetty.isCanceled();
        }
    }
}
