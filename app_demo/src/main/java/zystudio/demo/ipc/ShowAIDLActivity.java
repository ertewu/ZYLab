package zystudio.demo.ipc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;

import zystudio.demo.R;
import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/12.
 */

public class ShowAIDLActivity extends Activity {


    public static void showCase(Activity activity){
        Intent intent=new Intent(activity,ShowAIDLActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbinder);
    }


    public void onBindBtnClick(View view){
        Intent intent=new Intent(this,RemoteAIDLService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    //Bind Service 相关的类
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.log("onServiceConnected occured");
            RemoteAIDLService.MyBinder binder=(RemoteAIDLService.MyBinder) service;
            binder.invokeServiceMethod();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
