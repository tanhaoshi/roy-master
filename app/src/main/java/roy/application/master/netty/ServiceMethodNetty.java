package roy.application.master.netty;

import com.socks.library.KLog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.CallAdapter;
import retrofit2.Callback;
import roy.application.master.R;
import roy.application.master.annotation.Dispense;
import roy.application.master.annotation.Param;


final class ServiceMethodNetty<T> {

    private Builder mBuilder;

    public NettyFactory mFactory;

    public CallBack     mCallback;

    public ServiceMethodNetty(Builder builder){
        this.mBuilder  = builder;
        this.mFactory  = builder.mNettyFactory;
        this.mCallback = builder.mCallBack;
    }

    static final class Builder {
        final Method         mMethod;
        Annotation[]         mAnnotations;
        Annotation[][]       mAnnotationArray;
        Type                 mType;
        CallBack             mCallBack;
        NettyProxyAccess     mNettyProxyAccess;
        NettyFactory         mNettyFactory;

        Builder(Method method,NettyProxyAccess nettyProxyAccess){
            this.mMethod          = method;
            this.mAnnotations     = method.getAnnotations();
            this.mAnnotationArray = method.getParameterAnnotations();
            this.mType            = method.getGenericReturnType();
            this.mNettyProxyAccess= nettyProxyAccess;
        }

        public ServiceMethodNetty builder(){

            for(Annotation annotation : mAnnotations){
                parseMethodAnnotation(annotation);
            }

            parseParameterAnnotation();

            return new ServiceMethodNetty(this);
        }

        public Builder callNettyFactory(){
            CallBack callBack = mCallBack ;
            if(null == callBack){
                callBack = new NettyManager();
                this.mCallBack = callBack;
            }
            return this;
        }

        public Builder addCallAdapterFactory(NettyFactory.Factory factory){
            mNettyProxyAccess.mFactoryList.add(factory);
            this.mNettyFactory = nettyFactoryAdapter();
            return this;
        }

        private void parseMethodAnnotation(Annotation annotation) {
            if(annotation instanceof Dispense){
                KLog.i("annotation value = " + ((Dispense) annotation).value());
            }
        }

        public String parseParameterAnnotation(){
            String value = "";
            int parameterCount = mAnnotationArray.length;
            for(int p=0;p<parameterCount;p++){
                Annotation[] annotation = mAnnotationArray[p];
                if(null != annotation){
                    value = ((Param)annotation[0]).value();
                }
            }
            return value;
        }

        public NettyFactory nettyFactoryAdapter(){
            NettyFactory nettyFactory = null;
            int start = mNettyProxyAccess.mFactoryList.indexOf(null) + 1;
            for(int i=start,count = mNettyProxyAccess.mFactoryList.size();i<count;i++){
                nettyFactory = mNettyProxyAccess.mFactoryList.get(i).get(mType,mAnnotations);
            }
            return nettyFactory;
        }
    }

    public String toRequest(){
        return mBuilder.parseParameterAnnotation();
    }
}
