package zystudio.demo.cases;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import zystudio.demo.R;

public class CaseForTouch  {

    private static CaseForTouch sCase;
    private Activity mAct;

    public static CaseForTouch obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseForTouch();
        }
        sCase.setActivity(act);
        return sCase;
    }

    private void setActivity(Activity act) {
        this.mAct = act;
    }

    public void work() {
        mAct.setContentView(R.layout.casefortouch);
        RelativeLayout rl = (RelativeLayout)
         mAct.findViewById(R.id.casetouch_rl);
        rl.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                printMotionEvent(event);
                return false;
            }
        });
        ImageView iv = (ImageView) mAct.findViewById(R.id.casetouch_iv);
        iv.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                printMotionEvent(event);
                return false;
            }
        });
        iv.setImageResource(R.drawable.motor);
    }

    public void work2(){
        ImageView iv=new ImageView(mAct);
        iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        iv.setImageResource(R.drawable.motor);
        mAct.setContentView(iv);
        iv.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                printMotionEvent(event);
                return false;
            }
        });
    }

    //work1 work2 work3 都不能实现多点触控
    public void work3(){
        LinearLayout ll=new LinearLayout(mAct);
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        ll.setBackgroundResource(R.drawable.motor);
        mAct.setContentView(ll);
        ll.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                printMotionEvent(event);
                return false;
            }
        });
    }

    public void work4(){
        CustImageView iv=new CustImageView(mAct);
        iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        iv.setImageResource(R.drawable.motor);
        mAct.setContentView(iv);
    }

    private static void printMotionEvent(MotionEvent event) {

        //监测ImageView,完全没有检测出多点触摸的东西
        Log.i("ertewu", "event 1:" + event.getAction());
        Log.i("ertewu", "event 2:" + event.getActionIndex());
        Log.i("ertewu", "event 3:" + event.getPointerCount());
        Log.i("ertewu", "event 4:" + event.getPointerId(event.getActionIndex()));

        final int actionMasked = event.getAction() & MotionEvent.ACTION_MASK;
        Log.i("ertewu", "event 5:" +actionMasked);

        Log.i("ertewu", "=====================");
    }

    private static class CustImageView extends ImageView{

        public CustImageView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            printMotionEvent(event);
            return super.onTouchEvent(event);
        }


    }
}
