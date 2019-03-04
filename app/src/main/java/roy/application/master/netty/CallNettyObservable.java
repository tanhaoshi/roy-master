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
}
