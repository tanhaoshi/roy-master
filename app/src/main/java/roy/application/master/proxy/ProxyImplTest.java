package roy.application.master.proxy;


import com.socks.library.KLog;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import roy.application.master.annotation.Dispense;

/**
 * THE AUTHOR : TANHAOSHI
 * THE TIME   : 2019/1/12
 */
public final class ProxyImplTest {

    public static volatile ProxyImplTest sProxyImplTest;

    private ProxyImplTest(){
//        throw new IllegalAccessException("The ProxyImplTest is without construction");
    }

    public static ProxyImplTest getInstance(){
        if(sProxyImplTest == null){
            synchronized(ProxyImplTest.class){
                if(sProxyImplTest == null){
                    sProxyImplTest = new ProxyImplTest();
                }
            }
        }
        return sProxyImplTest;
    }

    /**  假设创建 neety + rxandroid 起步应该如何,首先应当从retrofit 中得create方法下手。需要自己创建callExecuteObservable么？还是需要创建得,不然当方法执行完毕
     *   后,还是会执行到retrofit当中去 我得步骤应该是创建我自己的retrofit
     *   不管重新构造这一个步骤，我们继续跟踪，当我执行execute这个接口的时候,
     * */
    public <T> T createService(final Class<T> service){
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Dispense dispense = method.getAnnotation(Dispense.class);
//                Log.i("ProxyImplTest","Dispense value = " +dispense.value());
//                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
//                for(int i=0;i<parameterAnnotations.length;i++){
//                    Annotation[] annotation = parameterAnnotations[i];
//                    if(annotation != null){
//                        Param param = (Param)annotation[0];
//                        Log.i("ProxyImplTest","Param value = " + param.value() + "result = " + args[i]);
//                    }
//                }
                for(Annotation annotation : method.getAnnotations()){
                    KLog.i("annotation value = " + ((Dispense)annotation).value());
                }
                return null;
            }
        });
    }
}
