package zystudio.demo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaseInvoke.invokeCase(this);
        // LinearLayout layout = new LinearLayout(this);
        // layout.setLayoutParams(new
        // ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,
        // LayoutParams.MATCH_PARENT));
        // layout.setOrientation(LinearLayout.VERTICAL);
        // EditText mEdit = new EditText(this);
        // mEdit.setLayoutParams(new
        // ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,
        // LayoutParams.WRAP_CONTENT));
        // layout.addView(mEdit);
        // setContentView(layout);
        // mEdit.setOnLongClickListener(new OnLongClickListener() {
        //
        // @Override
        // public boolean onLongClick(View v) {
        // return true;
        // }
        // });
    }
}
