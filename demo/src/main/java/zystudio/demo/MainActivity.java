package zystudio.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         CaseInvoke.invokeCase(this);
//        showDimenDemo();
        // setContentVIewByXml();
        // setMyContentView();
        // sogou.webkit.WebView my = new sogou.webkit.WebView(this);
        // testLoadLibMethod();
    }

    private void testLoadLibMethod() {
        String dataDir = getApplicationInfo().dataDir;
        Log.i("ZYStudio", "dataDir is:" + dataDir); // data/data/zystudio.demo
        String libha = System.mapLibraryName("haha");
        Log.i("ZYStudio", "libha is:" + libha); // libhaha.so
        String pName = "";
        System.load(pName);
        String lpName = "";
        System.loadLibrary(lpName);
    }

    private void showDimenDemo() {
        setContentView(R.layout.activity_main);

    }

    private void setContentVIewByXml() {
        Log.i("ZYStudio", "ContentView xml start:" + System.currentTimeMillis() % 10000);
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        Log.i("ZYStudio", "ContentView xml end:" + System.currentTimeMillis() % 10000);
        setContentView(contentView);

    }
    private void setMyContentView() {
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        // 7
        FrameLayout content = new FrameLayout(this);
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        FrameLayout content2 = new FrameLayout(this);
        Log.i("ZYStudio", "ContentView start 2:" + System.currentTimeMillis() % 10000);
        // 1
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        content.setLayoutParams(params);
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        // 19
        TextView tView = new TextView(this);
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        TextView tView2 = new TextView(this);
        Log.i("ZYStudio", "ContentView start3:" + System.currentTimeMillis() % 10000);
        ViewGroup.LayoutParams wParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        tView.setLayoutParams(wParams);
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        content.addView(tView);
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        View view = new View(this);
        Log.i("ZYStudio", "ContentView start:" + System.currentTimeMillis() % 10000);
        setContentView(content);
    }

    private static class Base {
        private int mValue;
        private String mStr;

        public Base() {

        }

        public void setValue(int value) {
            mValue = value;
        }

        public void setStr(String str) {
            mStr = str;
        }

        public Base(Base b) {
            mValue = b.mValue;
            mStr = b.mStr;
        }
    }

    static class A extends Base {

    }

    static class B extends Base {
        public B(A myA) {
            super(myA);
        }

    }
    
    void showDemo() {
        A myA=new A();
        myA.setStr("haha");
        myA.setValue(222);
        B myB = new B(myA);
    }

    public static Activity getActivity() {
        return null;
    }
}
