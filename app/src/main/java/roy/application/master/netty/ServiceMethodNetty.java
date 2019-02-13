package roy.application.master.netty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import roy.application.master.proxy.ProxyImplTest;

final class ServiceMethodNetty {

    public ServiceMethodNetty(Builder builder){

    }

    static final class Builder{
        final ProxyImplTest  mProxyImplTest;
        final Method         mMethod;
        Annotation[]         mAnnotations;
        Annotation[][]       mAnnotationArray;
        Type[]               mTypes;

        Builder(ProxyImplTest proxyImplTest,Method method){
            this.mProxyImplTest   = proxyImplTest;
            this.mMethod          = method;
            this.mAnnotations     = method.getAnnotations();
            this.mAnnotationArray = method.getParameterAnnotations();
            this.mTypes           = method.getGenericParameterTypes();
        }

        public ServiceMethodNetty builder(){
            //这里要处理事情了,这里我要处理我的工作集合,去连接我的netty管理
            return new ServiceMethodNetty(this);
        }
    }
}
