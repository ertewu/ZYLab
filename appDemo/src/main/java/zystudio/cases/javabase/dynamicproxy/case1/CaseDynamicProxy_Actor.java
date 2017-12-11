package zystudio.cases.javabase.dynamicproxy.case1;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by wzy on 8/5/17.
 * 例子来自
 * http://blog.csdn.net/ffm83/article/details/43699619
 */

public class CaseDynamicProxy_Actor {

    public void work() {

        ActorProxy proxy = new ActorProxy();

        Actor p = proxy.getProxy();

        LogUtil.log("Proxy Actor real Cls:" + proxy.getProxy().getClass().getName());
        LogUtil.log("Proxy Actor real Cls is Actor?:" + (proxy.getProxy() instanceof Actor));
        LogUtil.log("Proxy Actor real Cls is BaoQiang Actor?:" + (proxy.getProxy() instanceof WangBaoQiang));
        LogUtil.log("----------------------------------");

        /*
         看结果：
         08-05 18:30:07.363 1645-1645/? I/ZYStudio: Proxy Actor real Cls:$Proxy0
　　　　　08-05 18:30:07.363 1645-1645/? I/ZYStudio: Proxy Actor real Cls is Actor?:true
　　　　　08-05 18:30:07.363 1645-1645/? I/ZYStudio: Proxy Actor real Cls is BaoQiang Actor?:false
         说明这个代理类，确实是造出来的一个新类
         */

        String retValue = p.sing("Two tiggeers");
        LogUtil.log("Case Actor sing:" + retValue);

        retValue = p.dance("Two monkey");
        LogUtil.log("Case Actor dance:" + retValue);
    }

}
