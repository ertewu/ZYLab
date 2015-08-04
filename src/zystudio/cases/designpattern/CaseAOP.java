package zystudio.cases.designpattern;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import android.util.Log;

/**
 * AOP的例子
 */
public class CaseAOP {

    private static CaseAOP sCase;

    public class HelloWorld {
        public void sayHelloWorld() {
            Log.i("ZYStudio", "HelloWorld!");
        }
    }

    public static CaseAOP obtain() {
        if (sCase == null) {
            sCase = new CaseAOP();
        }
        return sCase;
    }

    public class CgLibProxy implements MethodInterceptor {
        private Object obj;

        public Object createProxy(Object target) {
            this.obj = target;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(this.obj.getClass());
            enhancer.setCallback(this);
            enhancer.setClassLoader(target.getClass().getClassLoader());
            return enhancer.create();
        }

        @Override
        public Object intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
            Object result = null;
            doBefore();
            result = methodProxy.invokeSuper(proxy, params);
            doAfter();
            return result;
        }

        private void doBefore() {
            Log.i("ZYStudio", "before method invoke");
        }

        private void doAfter() {
            Log.i("ZYStudio", "after method invoke");
        }
    }

    public void performTest() {
        HelloWorld helloWorld = new HelloWorld();
        CgLibProxy cglibProxy = new CgLibProxy();
        HelloWorld hw = (HelloWorld) cglibProxy.createProxy(helloWorld);
        hw.sayHelloWorld();
    }
}