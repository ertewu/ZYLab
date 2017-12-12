package zystudio.demo.activitylifecycle2;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by zylab on 2017/12/3.
 */

public class CaseActivityLifeCycle2 {

    public static void showCase(Activity activity){
        activity.startActivity(new Intent(activity, Cycle2ActivityA.class));
    }
}
