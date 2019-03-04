package roy.application.master.netty;

 interface CallBack {

    void onRequest(NettyRequest nettyRequest);

    void onResponse(NettyResponse nettyResponse);

    void onFailure(Throwable errorMessage);

    void onConnectState(boolean state);

}
