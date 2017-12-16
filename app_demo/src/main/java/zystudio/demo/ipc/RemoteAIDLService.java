package zystudio.demo.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/12.
 */

public class RemoteAIDLService extends Service {


    public void serviceMethod(){
        LogUtil.log("BindSerivce -> MyMethod");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends Binder {

        public void invokeServiceMethod(){
           serviceMethod();
        }

    }

    private MyBinder myBinder=new MyBinder();
}
