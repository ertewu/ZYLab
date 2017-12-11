package zystudio.cases.androidmechanism;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import zystudio.mylib.utils.LogUtil;


/**
 * 这是一个静态注册的Receiver，用来看这个Context到底是什么
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    /**
     08-05 09:42:41.842 2622-2622/? I/ZYStudio: NetworkChange onReceive:android.app.ReceiverRestrictedContext
     08-05 09:42:41.842 2622-2622/? I/ZYStudio: NetworkChange onReceive baseCtx:zystudio.demo.ZYDemoApp
     08-05 09:42:41.982 2622-2622/? I/ZYStudio: NetworkChange onReceive:android.app.ReceiverRestrictedContext
     08-05 09:42:41.982 2622-2622/? I/ZYStudio: NetworkChange onReceive baseCtx:zystudio.demo.ZYDemoApp
     看看打出来的东西，确实就是我的ZYDemoApp了
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.log("NetworkChange onReceive:" + context.getClass().getName());
        Context baseCtx = ((ContextWrapper) context).getBaseContext();
        LogUtil.log("NetworkChange onReceive baseCtx:" + baseCtx.getClass().getName());
    }

}
