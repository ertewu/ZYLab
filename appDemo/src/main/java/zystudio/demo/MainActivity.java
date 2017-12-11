package zystudio.demo;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import zystudio.mylib.utils.LogUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         CaseInvoke.invokeCase(this);
//        setContentView(R.layout.activity_main);
    }

    private void testLoadLibMethod() {
        String dataDir = getApplicationInfo().dataDir;
//      LogUtil.log("dataDir is:" + dataDir); // data/data/zystudio.demo
        String libha = System.mapLibraryName("haha");
//        LogUtil.log("libha is:" + libha); // libhaha.so
        String pName = "";
        System.load(pName);
        String lpName = "";
        System.loadLibrary(lpName);

    }

}
