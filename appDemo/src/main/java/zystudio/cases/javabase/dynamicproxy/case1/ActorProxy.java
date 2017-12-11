package zystudio.cases.javabase.dynamicproxy.case1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by wzy on 8/5/17.
 */

public class ActorProxy {

    private Actor mActor = new WangBaoQiang();

    public Actor getProxy() {

        Object proxy = Proxy.newProxyInstance(
                ActorProxy.class.getClassLoader(),
                mActor.getClass().getInterfaces(),
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("sing")) {
                            LogUtil.log("This is Actor proxy");
                            return method.invoke(mActor, args);
                        }
                        if (method.getName().equals("dance")) {
                            LogUtil.log("This is Actor proxy");
                            return method.invoke(mActor, args);
                        }
                        return null;
                    }
                });
        return (Actor) proxy;
    }

}
