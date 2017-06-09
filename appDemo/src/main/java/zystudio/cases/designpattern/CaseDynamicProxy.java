package zystudio.cases.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.util.Log;

/**
 * 动态代理的例子，但是动态代理必须先实现一个接口，这不是我想要的．．
 * 关于动态代理与装饰模式的区别：其实仍然不是很清楚，但是可以记一下：
 * http://stackoverflow.com/questions/18618779/differences-between-proxy-and-decorator-pattern
 * 其实这个更好，可以说明的是，这个动态代理可以调用很多函数，这样能彰显一下这个模式的价值..
 * http://www.codekk.com/open-source-project-analysis/detail/Android/Caij/%E5%85%AC%E5%85%B1%E6%8A%80%E6%9C%AF%E7%82%B9%E4%B9%8BJava%20%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86
 */
public class CaseDynamicProxy {

    private static CaseDynamicProxy sCase;
    public static CaseDynamicProxy obtain(){
        if(sCase==null){
            sCase=new CaseDynamicProxy();
        }
        return sCase;
    }

    public interface Subject {
        public void doSomething();

        public void doSth2();

        public void doSth3();
    }

    public static class RealSubject implements Subject {

        @Override
        public void doSomething() {
            Log.i("ZYStudio", "RealSubject call doSomething()");
        }

        @Override
        public void doSth2() {
            Log.i("ZYStudio", "RealSubject call sth2");

        }

        @Override
        public void doSth3() {
            Log.i("ZYStudio", "RealSubject call sth3");
        }
    }

    public static class ProxyHandlerImpl implements InvocationHandler {

        private Object mProxied;

        public ProxyHandlerImpl() {
            this.mProxied = new RealSubject();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(mProxied, args);
        }
    }

    public void perform() {
        Subject proxySubject = (Subject) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { Subject.class }, new ProxyHandlerImpl());
        proxySubject.doSomething();
        proxySubject.doSth2();
        proxySubject.doSth3();
        Log.i("ZYStudio","proxySubject hash:"+proxySubject.hashCode());
    }
}