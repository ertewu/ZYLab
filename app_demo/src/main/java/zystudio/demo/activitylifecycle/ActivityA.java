package zystudio.demo.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import zystudio.demo.R;
import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/11/23.
 * 由demo可见，实际上onStop 走得在三个activity里并不串行..
 */

public class ActivityA extends Activity{

    private String getName(){
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,ActivityB.class));
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
