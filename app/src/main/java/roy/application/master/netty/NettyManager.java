package roy.application.master.netty;


import com.alibaba.fastjson.JSON;

public class NettyManager implements CallBack{

    private NettySocket mNettySocket;

    public NettyManager(){
        if(null == mNettySocket) mNettySocket = new NettySocket(this);
    }

    private String convertParameterAnnotation(String body){
        if("".equals(body) || null == body || "".equals(body.trim()) || body.length() == 0){
            throw new IllegalArgumentException("The body is null of chars");
        }
        String convert = JSON.toJSONString(body);
        return convert;
    }

    @Override
    public void onRequest(NettyRequest nettyRequest) {
        String  body = convertParameterAnnotation(nettyRequest.getMessage());
        mNettySocket.sendData(body);
    }

    @Override
    public void onResponse(NettyResponse nettyResponse) {

    }

    @Override
    public void onFailure(Throwable errorMessage) {

    }

    @Override
    public void onConnectState(boolean state) {

    }
}
