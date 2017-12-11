package zystudio.demo.threeactivitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

        /**
         * 下边这个实例，说明确实是SecondActivity的onResume之后，才走FirstActivity的onStop的:
         * 流程如下：
         * 08-06 10:01:54.964 24316-24316/? I/ZYStudio: Activity:FirstActivity|onCreate
        　 08-06 10:01:54.974 24316-24316/? I/ZYStudio: Activity:FirstActivity|onStart
         　08-06 10:01:54.974 24316-24316/? I/ZYStudio: Activity:FirstActivity|onResume
         　08-06 10:01:57.994 24316-24316/? I/ZYStudio: Activity:FirstActivity|onPause
         　08-06 10:01:58.014 24316-24316/? I/ZYStudio: Activity:SecondActivity|onCreate
         　08-06 10:01:58.014 24316-24316/? I/ZYStudio: Activity:SecondActivity|onStart
         　08-06 10:01:58.014 24316-24316/? I/ZYStudio: Activity:SecondActivity|onResume
         　08-06 10:01:58.554 24316-24316/? I/ZYStudio: Activity:FirstActivity|onStop
         */
//        (new Handler()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
//            }
//        }, 3000);


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
