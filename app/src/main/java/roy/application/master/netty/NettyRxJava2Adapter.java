package roy.application.master.netty;

import io.reactivex.Observable;

final class NettyRxJava2Adapter<R> implements NettyFactory<R,Object>{

    @Override
    public Object adapter(CallNetty<R> callNetty) {
        Observable<NettyResponse<R>> responseNettyObservable
                = new CallNettyObservable<>(callNetty);
        return responseNettyObservable;
    }

    public NettyRxJava2Adapter(){
    }

}
