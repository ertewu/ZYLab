package zystudio.demo.threeactivitylifecycle;

import android.content.Context;
import android.content.Intent;

/**
 * Created by leeco on 2017/7/4.
 */

public class CaseMultiActivityLifeCycle {

    private Context mContext;

    public CaseMultiActivityLifeCycle(Context context) {
        mContext = context;
    }

    public void work() {
        mContext.startActivity(new Intent(mContext, FirstActivity.class));
    }
}
