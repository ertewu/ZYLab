package zystudio.demo.activitylifecycle2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import zystudio.demo.R;
import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/11/23.
 */

public class Cycle2ActivityB extends Activity{

    private String getName(){
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log(getName()+"|onCreate");
        setContentView(R.layout.activity_main);
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
