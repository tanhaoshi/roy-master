package roy.application.master.netty;

 interface CallBack<T> {

    void onRequest(NettyRequest nettyRequest);

    void onResponse(NettyResponse<T> nettyResponse);

    void onFailure(Throwable errorMessage);

    void onConnectState(boolean state);

}
