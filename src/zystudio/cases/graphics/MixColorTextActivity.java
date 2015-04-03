package zystudio.cases.graphics;

import zystudio.demo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 研究一下今日头条的滑动反色是怎么做的
 */
public class MixColorTextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mixcolor);
        TextView myTextView=(TextView) findViewById(R.id.my_txtview);
        FrameLayout myLayout=(FrameLayout) findViewById(R.id.myframe);
    }
    public static void startMixColorActivity(Activity activity){
        activity.startActivity(new Intent(activity,MixColorTextActivity.class));
    }

}
