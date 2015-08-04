package zystudio.cases.architecture;

import android.util.Log;

public class CaseShow {

    public static void show() {
        Case2 myCase = (Case2) Case2.getInstance();
        Log.i("ZYStudio", "myCase is:"+myCase.getClass().getName());
    }
}
