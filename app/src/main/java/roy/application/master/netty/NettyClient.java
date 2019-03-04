package roy.application.master.netty;

final class NettyClient<T> implements CallNetty<T>{

    private ServiceMethodNetty mServiceMethodNetty;

    public NettyClient(ServiceMethodNetty<T> serviceMethodNetty){
        this.mServiceMethodNetty = serviceMethodNetty;
    }

    @Override
    public void cancel() {

    }

    @Override
    public CallNetty<T> clone() {
        return new NettyClient<>(mServiceMethodNetty);
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void execute() {
        if(null == mServiceMethodNetty.mCallback){
            throw new NullPointerException("The interface is null of CallBack");
        }
        mServiceMethodNetty.mCallback.onRequest(createNettyRequest());
    }

    private NettyRequest createNettyRequest(){
        if(null == mServiceMethodNetty){
             throw new NullPointerException("The Service method is null of ServiceMethodNetty");
        }
        NettyRequest nettyRequest = new NettyRequest.Builder()
                .addBody(mServiceMethodNetty.toRequest())
                .builder();

        return nettyRequest;
    }
}
