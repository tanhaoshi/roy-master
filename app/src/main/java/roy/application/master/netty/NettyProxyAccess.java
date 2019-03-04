package roy.application.master.netty;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class NettyProxyAccess {

    public static volatile NettyProxyAccess sNettyProxyAccess;

    private final Map<Method,ServiceMethodNetty> mMethodNettyMap = new ConcurrentHashMap<>();

    public final List<NettyFactory.Factory> mFactoryList = new ArrayList<>();

    private NettyProxyAccess(){}

    public static NettyProxyAccess getInstance(){
        if(null == sNettyProxyAccess){
            synchronized(NettyProxyAccess.class){
                if(null == sNettyProxyAccess){
                    sNettyProxyAccess = new NettyProxyAccess();
                }
            }
        }
        return sNettyProxyAccess;
    }

    public <T> T createService(final Class<T> service){
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ServiceMethodNetty serviceMethodNetty = loadServiceMethod(method);
                NettyClient<Object> nettyClient = new NettyClient<>(serviceMethodNetty);
                return serviceMethodNetty.mFactory.adapter(nettyClient);
            }
        });
    }

    ServiceMethodNetty loadServiceMethod(Method method){
        ServiceMethodNetty result = mMethodNettyMap.get(method);
        if(null != result) return result;

        synchronized (mMethodNettyMap){
            result = mMethodNettyMap.get(method);
            if(null == result){
                result = new ServiceMethodNetty.Builder(method,this).
                        callNettyFactory().
                        addCallAdapterFactory(NettyRxJava2CallAdapterFactory.create()).
                        builder();
                mMethodNettyMap.put(method,result);
            }
        }
        return result;
    }

}
