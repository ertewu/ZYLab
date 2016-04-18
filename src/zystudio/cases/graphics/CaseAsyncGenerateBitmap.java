package zystudio.cases.graphics;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * 用来实验在线程中，可否用Ｃanvas　Draw来生成Ｂitmap的
 */
public class CaseAsyncGenerateBitmap {

    private static CaseAsyncGenerateBitmap sCase;
    private Activity mAct;
    private Paint mTextPaint;

    public static CaseAsyncGenerateBitmap getInstance(Activity activity) {
        if (sCase == null) {
            sCase = new CaseAsyncGenerateBitmap();
        }
        sCase.init(activity);
        return sCase;
    }

    private void init(Activity act) {
        mAct = act;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                ImageView imgView = new ImageView(mAct);
                imgView.setImageBitmap(mBitmap);
                mAct.setContentView(imgView);
            }
        }
    };
    private Bitmap mBitmap;
    public void work() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 果然是可以异步画位图的
                mBitmap = asyncDrawBitmap();
                // mHandler.sendEmptyMessage(100);
                mHandler.sendEmptyMessageDelayed(100, 5000);
            }
        }).start();
    }

    private Bitmap asyncDrawBitmap() {
        Bitmap img = Bitmap.createBitmap(500, 500, Config.RGB_565);
        Canvas myCanvas = new Canvas(img);
        mTextPaint = new Paint();
        // 这个是像素
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(android.graphics.Color.RED);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        // 字是从右向左画的？这个文字的，算不出来坐标是怎么回事，底下那个示例更能反应问题
        myCanvas.drawText("好啊", 100, 100, mTextPaint);
        return img;
    }

}