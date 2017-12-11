package zystudio.demo.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import zystudio.demo.R;
import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/11/23.
 */

public class ActivityC extends Activity{

    private String getName(){
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.log(getName()+"|onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.log(getName()+"|onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.log(getName()+"|onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.log(getName()+"|onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.log(getName()+"|onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.log(getName()+"|onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.log(getName()+"|onDestory");
    }
}
