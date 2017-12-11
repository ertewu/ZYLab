package zystudio.demo.threeactivitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by leeco on 2017/7/4.
 */

public class FirstActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log("Activity:" + getClass().getSimpleName() + "|onCreate");

        this.startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.log("Activity:" + getClass().getSimpleName() + "|onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.log("Activity:" + getClass().getSimpleName() + "|onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.log("Activity:" + getClass().getSimpleName() + "|onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.log("Activity:" + getClass().getSimpleName() + "|onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.log("Activity:" + getClass().getSimpleName() + "|onDestory");
    }
}
