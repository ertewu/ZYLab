package zystudio.demo;

import android.app.Application;
import android.content.Context;

public class ZYDemoApp extends Application {
    
    public static Context sAppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }
}
