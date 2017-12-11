package zystudio.demo.activitylifecycle;

import android.content.Context;
import android.content.Intent;

/**
 * Created by zylab on 2017/11/23.
 */

public class CaseThreeActivityStart {

    public static void work(Context ctx){
        ctx.startActivity(new Intent(ctx,ActivityA.class));
    }
}
