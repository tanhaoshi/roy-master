package roy.application.master.netty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class NettyRxJava2CallAdapterFactory extends NettyFactory.Factory{

    public static NettyRxJava2CallAdapterFactory create(){
        return new NettyRxJava2CallAdapterFactory();
    }

    private NettyRxJava2CallAdapterFactory(){}

    @Override
    public NettyFactory get(Type returnType, Annotation[] annotations) {
        return new NettyRxJava2Adapter();
    }


}
