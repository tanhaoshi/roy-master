package roy.application.master.netty;

public final class NettyRequest {

    private String message;

    NettyRequest(Builder builder){
        this.message = builder.message;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder{

        String message;

        public Builder(){
        }

        public Builder addBody(String message){
            this.message = message;
            return this;
        }

        public NettyRequest builder(){
            return new NettyRequest(this);
        }
    }
}
