package roy.application.master.netty;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface NettyFactory<R,T> extends Cloneable{

    T adapter(CallNetty<R> callNetty);

    abstract class Factory{
        public abstract NettyFactory get(Type returnType , Annotation[] annotations);
    }

}
