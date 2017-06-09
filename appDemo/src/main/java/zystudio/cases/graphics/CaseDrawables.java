package zystudio.cases.graphics;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import zystudio.demo.R;

public class CaseDrawables {

    private static CaseDrawables sCase;
    private Activity mAct;

    public static CaseDrawables obtain(Activity activity) {
        if (sCase == null) {
            sCase = new CaseDrawables();
            sCase.mAct = activity;
        }
        return sCase;
    }

    public void work() {
        // testLevelListDrawable();
        testClipDrawable();
        // testScaleDrawable();
    }

    private void testLevelListDrawable() {
        sTimerCount = 0;
        mAct.setContentView(R.layout.case_drawable);
        ImageView imageview = (ImageView) mAct.findViewById(R.id.drawable_demo);
        final LevelListDrawable drawable = (LevelListDrawable) mAct
                .getResources().getDrawable(R.drawable.layer_img);
        imageview.setImageDrawable(drawable);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x1233) {
                    // 修改ClipDrawable的level值
                    drawable.setLevel(sTimerCount % 3 + 1);
                }
            }
        };
        final Timer timer = new Timer();
        // Timer 300毫秒才走一次..
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sTimerCount++;
                Message msg = new Message();
                msg.what = 0x1233;
                handler.sendMessage(msg);
            }
        }, 0, 1000);
    }

    /**
     * ScaleDrawable也有几个自身属性：scaleHeight,scaleWidth,scaleGravity
     */
    private void testScaleDrawable() {
        mAct.setContentView(R.layout.case_drawable);
        ImageView imageview = (ImageView) mAct.findViewById(R.id.drawable_demo);
        final ScaleDrawable scaledrawable = (ScaleDrawable) mAct.getResources()
                .getDrawable(R.drawable.scale_img);
        imageview.setImageDrawable(scaledrawable);
        /**
         * scaleDrawable也得用setLevel这个函数..如果什么都不用的话就显示不出来,
         * 当然我也没想具体的比例是怎么回事.我似乎发现不设level就显示不出来，不同level显示比例我也没算
         */
        scaledrawable.setLevel(5000);
    }

    /**
     * 从这里找到的：http://blog.csdn.net/lee576/article/details/7827676 clipDrawable
     * 这个drawable自身特点的可用XML声明的属性就只有三个: gravity、clipOrientation、drawable
     */
    private void testClipDrawable() {
        mAct.setContentView(R.layout.case_drawable);
        ImageView imageview = (ImageView) mAct.findViewById(R.id.drawable_demo);
        final ClipDrawable clipdrawable = (ClipDrawable) mAct.getResources()
                .getDrawable(R.drawable.clip_img);
        // 获取图片所显示的ClipDrawble对象
        imageview.setImageDrawable(clipdrawable);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x1233) {
                    // 修改ClipDrawable的level值
                    clipdrawable.setLevel(clipdrawable.getLevel() + 200);
                }
            }
        };
        final Timer timer = new Timer();
        // Timer 300毫秒才走一次..
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("ertewu",
                        "timer:" + sTimerCount + "|"
                                + System.currentTimeMillis() % 10000);
                sTimerCount++;
                Message msg = new Message();
                msg.what = 0x1233;
                // 发送消息,通知应用修改ClipDrawable对象的level值
                handler.sendMessage(msg);
                // 取消定时器
                if (clipdrawable.getLevel() >= 10000) {
                    timer.cancel();
                }
            }
        }, 0, 300);
    }

    private static int sTimerCount;
}
