package roy.application.master.netty;

import io.reactivex.disposables.Disposable;

public class CallNettyDisposableDispatcher implements Disposable,DispatcherCall {

    @Override
    public void dispose() {}

    @Override
    public boolean isDisposed() {return false; }

    @Override
    public void onRequest(NettyRequest nettyRequest) {}

    @Override
    public void onResponse(NettyResponse nettyResponse) {}

    @Override
    public void onFailure(Throwable errorMessage) {}

    @Override
    public void onConnectState(boolean state) {}
}
