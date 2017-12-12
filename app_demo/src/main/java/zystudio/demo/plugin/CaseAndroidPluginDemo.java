package zystudio.demo.plugin;

import android.content.Context;
import android.content.Intent;

/**
 * Created by leeco on 2017/7/29.
 */

public class CaseAndroidPluginDemo {

    public void work(Context context) {
        context.startActivity(new Intent(context, ClsLoaderActivity.class));
    }
}
