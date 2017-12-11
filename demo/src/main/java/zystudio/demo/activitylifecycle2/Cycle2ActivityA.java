package zystudio.demo.activitylifecycle2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import zystudio.demo.R;
import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/11/23.
 * 由demo可见，实际上onStop 走得在三个activity里并不串行..
 */
//果然是 B的onResume之后, A才走的onStop
public class Cycle2ActivityA extends Activity{

    private String getName(){
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log(getName()+"|onCreate");
        setContentView(R.layout.activity_main);
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Cycle2ActivityA.this,Cycle2ActivityB.class));
            }
        },5000);
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
