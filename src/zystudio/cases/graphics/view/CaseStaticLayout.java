package zystudio.cases.graphics.view;

import zystudio.demo.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

/** 用于了解android.text.StaticLayout或android.text.DynamicLayout的使用方法 */
//从这个例子看，StaticLayout改一下行距和断行还行，其实就不太行了..我想要的文字间隔根本没有,主要是去分行的
public class CaseStaticLayout {

    private static CaseStaticLayout sCase;
    private String mStr=null;
    private Activity mActivity;

    public static CaseStaticLayout getInstance(Activity act) {
        if (sCase == null) {
            sCase = new CaseStaticLayout(act);
        }
        return sCase;
    }

    private CaseStaticLayout(Activity activity) {
        mActivity=activity;
        mStr=mActivity.getResources().getString(R.string.str_demo);
    }

    public void work(){
        SampleView view=new SampleView(mActivity);
        view.setText(mStr);
        mActivity.setContentView(view);
    }

    public static class SampleView extends View{
        private String mStr;
        public SampleView(Context context) {
            super(context, null);
        }

        public void setText(String str){
            mStr=str;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            TextPaint tp=new TextPaint(Color.BLACK);
            tp.setTextSize(50);
            StaticLayout sl=new StaticLayout(mStr,tp,1080,Layout.Alignment.ALIGN_NORMAL,2.0f,20.0f,true);
            Log.i("ZYStudio", "Canvas:"+canvas.getClass().getName());
            sl.draw(canvas);
        }
    }
}
