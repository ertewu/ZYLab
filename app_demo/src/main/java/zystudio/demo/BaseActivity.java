package zystudio.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.ConcurrentHashMap;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by leeco on 2017/6/13.
 */

public class BaseActivity extends FragmentActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printClsName();
    }

    private void printClsName() {
//        String clsName=this.getClass().getSimpleName();
//        LogUtil.log("clsName is:"+clsName);
//
//        String clsName2=BaseActivity.this.getClass().getSimpleName();
//        LogUtil.log("clsName2 is:"+clsName2);
//
//        String clsName3=BaseActivity.this.getClass().getSuperclass().getSimpleName();
//        LogUtil.log("clsName3 is:"+clsName3);
    }
}
