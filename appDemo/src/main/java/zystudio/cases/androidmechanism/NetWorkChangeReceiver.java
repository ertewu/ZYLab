package zystudio.cases.androidmechanism;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/3.
 * http://blog.csdn.net/anerle2012/article/details/51253374
 * 从这篇文章来看， 其实， 收到ReceiverRestrictedContext 是因为在androidManifest.xml中注册的。
 * 如果在Activity中动态注册Receiver的话，那么返回的context是那个Activity实例
 */

public class NetWorkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.log("onReceive intent is:"+intent.getAction());
        /**
         * 线程 main   Context也是  android.app.ReceiverRestrictedContext ，跟ScreenReceiver那个并不一样
         */
        LogUtil.log("NetworkReceiver BroadCast occurd:"+Thread.currentThread().getName()+"|"+context.getClass().getName());
    }
}
