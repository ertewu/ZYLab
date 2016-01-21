package zystudio.utils;

import zystudio.demo.ZYDemoApp;
import android.widget.Toast;

public class Util {

    public static void showMsg(String str) {
        Toast.makeText(ZYDemoApp.sAppContext,str,Toast.LENGTH_SHORT).show();
    }

}
