package zystudio.demo;

import zystudio.mylib.utils.LogUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.log("onReceive intent is:"+intent.getAction());
        /**
         * 线程 main   Context是  android.app.ReceiverRestrictedContext
         */
        LogUtil.log("ScreenReceiver BroadCast occurd:"+Thread.currentThread().getName()+"|"+context.getClass().getName());
    }
}
