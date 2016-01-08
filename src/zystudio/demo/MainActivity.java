package zystudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ZYStudio", "onCreate start:" + System.currentTimeMillis() % 10000);
        super.onCreate(savedInstanceState);
        Log.i("ZYStudio", "onCreate end:" + System.currentTimeMillis() % 10000);
        CaseInvoke.invokeCase(this);
        // setContentVIewByXml();
        // setMyContentView();
        // sogou.webkit.WebView my = new sogou.webkit.WebView(this);
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
