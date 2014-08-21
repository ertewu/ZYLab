package zystudio.demo;

import zystudio.mylib.utils.LogUtil;
import android.app.Activity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 一些小型的只有一个函数的case，放在这里就好了，与ＭainActivity形成关联关系
 */
class MicroCaseHelper {

    /**
     * 在做自定义EditText弹出菜单的时候，看一下一些系统的case
     */
    public static void showEditTextContextMenu(Activity activity) {
        LinearLayout layout = new LinearLayout(activity);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText mEdit = new EditText(activity);
        mEdit.setLayoutParams(new ViewGroup.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        layout.addView(mEdit);
        activity.setContentView(layout);
        mEdit.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    /**
     * 这个只是用来验证一下View.getRootView方法，还有一个TextView的setIncludeFontPadding方法
     */
    public static void displayNormalCase(Activity activity) {
        TextView view = new TextView(activity);
        view.setText("添加到收藏");
        view.setTextSize(24);
        view.setIncludeFontPadding(true);
        view.setTextColor(android.graphics.Color.WHITE);
        view.setBackgroundColor(android.graphics.Color.BLUE);

        TextView view2 = new TextView(activity);
        view2.setText("添加到收藏");
        view2.setTextSize(24);
        view2.setIncludeFontPadding(false);
        view2.setBackgroundColor(android.graphics.Color.RED);
        view2.setTextColor(android.graphics.Color.WHITE);

        LinearLayout ll = new LinearLayout(activity);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(view);
        ll.addView(view2);
        activity.setContentView(ll);

        LogUtil.log(view.getRootView().getClass().getName());
        if (view.getRootView() == activity.getWindow().getDecorView()) {
            LogUtil.log("true");
        }
    }
}
