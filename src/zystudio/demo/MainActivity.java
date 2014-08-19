package zystudio.demo;

import zystudio.mylib.utils.LogUtil;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    /**
     * 这个只是用来验证一下View.getRootView方法，还有一个TextView的setIncludeFontPadding方法
     */
    private void displayNormalCase() {
        TextView view = new TextView(this);
        view.setText("添加到收藏");
        view.setTextSize(24);
        view.setIncludeFontPadding(true);
        view.setTextColor(android.graphics.Color.WHITE);
        view.setBackgroundColor(android.graphics.Color.BLUE);

        TextView view2 = new TextView(this);
        view2.setText("添加到收藏");
        view2.setTextSize(24);
        view2.setIncludeFontPadding(false);
        view2.setBackgroundColor(android.graphics.Color.RED);
        view2.setTextColor(android.graphics.Color.WHITE);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(view);
        ll.addView(view2);
        setContentView(ll);

        LogUtil.log(view.getRootView().getClass().getName());
        if (view.getRootView() == getWindow().getDecorView()) {
            LogUtil.log("true");
        }
    }
}
