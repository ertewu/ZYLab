package zystudio.cases.androidmechanism;

import java.io.File;
import java.lang.reflect.Constructor;

import dalvik.system.DexClassLoader;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class CaseLoadExternalApkActivity extends Activity {

    private final static String APK_PATH = "/mnt/sdcard/0_MyDir/SGBrowser.apk";
    private final static String OPTM_DIR_PATH="/mnt/sdcard/0_MyDir";
    private final static String ACTIVITY_NAME="sogou.mobile.explorer.BrowserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        Button btn = new Button(this);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    launchBrowserActivity();
                }catch(Exception e){
                    Log.i("ZYStudio", "exception :"+e.getMessage());
                }
            }
        });
        layout.addView(btn, new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(layout);
    }


    private void launchBrowserActivity() throws Exception{
        File dexFile=new File(APK_PATH);
        DexClassLoader dexLoader=new DexClassLoader(dexFile.getAbsolutePath(),OPTM_DIR_PATH,null,ClassLoader.getSystemClassLoader());
        Class<?> myCls=dexLoader.loadClass(APK_PATH);
        Constructor<?> myClsConstrucor=myCls.getConstructor(new Class[] {});
        Object instance=myClsConstrucor.newInstance(new Object[] {});

    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, CaseLoadExternalApkActivity.class));
    }
}
