package roy.application.master.netty;


public final class NettyResponse<T> {

    private String content;
    private String errorMessage;

    public NettyResponse(Builder builder){
        this.content      = builder.content;
        this.errorMessage = builder.errorMessage;
    }

    public static class Builder{
        String content;
        String errorMessage;

        Builder(){}

        public Builder setContent(String content){
            this.content = content;
            return this;
        }

        public Builder setErrorMessage(String error){
            this.errorMessage = error;
            return this;
        }

        public NettyResponse builder(){
            return new NettyResponse(this);
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
