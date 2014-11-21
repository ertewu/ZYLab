package zystudio.cases.graphics;

import zystudio.demo.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 以前DemoForPaint那个工程里的东西，其实东西很少，那个工程去掉了，与这个包合并了
 * @author wzy
 */
public class PaintActivity extends Activity {

    private static final int DEMO_NONE = 0;
    private static final int DEMO_MENU = 1;

    private static final String NINE_PATCH_URL = "http://10.129.156.208/skin.9.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // switchToDemo(DEMO_NONE);
        // showLayerDrawable();
        // testDrawableByPath();
        backgroundDemo();
    }

    public void testScaleTypeMatrix() {
        setContentView(R.layout.testbg);
        // FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set bkg
        ImageView imgView = (ImageView) findViewById(R.id.iv);
        imgView.setBackgroundResource(R.drawable.good_song);
        imgView.setScaleType(ScaleType.MATRIX);

        Matrix mat = new Matrix();
        // imgView.setImageMatrix(matrix);
    }

    public void backgroundDemo() {
        setContentView(R.layout.testbg);
        // FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set bkg
        // ImageView imgView = (ImageView) findViewById(R.id.iv);
        // imgView.setBackgroundResource(R.drawable.good_song);
    }

    /**
     * 这个demo测试表明，Drawable.createFromPath的速度是挺快的，看上去不用用线程，只要不是位图，
     * 用这个去loadDrawable也很快
     */
    public void testDrawableByPath() {
        Log.i("ertewu", "" + SystemClock.currentThreadTimeMillis() % 10000);
        Drawable img = Drawable.createFromPath("/mnt/sdcard/skin.9.png");
        Log.i("ertewu", "" + SystemClock.currentThreadTimeMillis() % 10000);
        getWindow().setBackgroundDrawable(img);
        Log.i("ertewu", "" + SystemClock.currentThreadTimeMillis() % 10000);
    }

    /**
     * 程序制作应该是可以的： http://blog.csdn.net/hudashi/article/details/6957160
     */
    public void showNinePatchDrawable() {

    }

    public void showLayerDrawable() {
        FrameLayout mContentRoot = new FrameLayout(this);
        mContentRoot.setBackgroundResource(R.drawable.layerdrawable);
        setContentView(mContentRoot);
        Bitmap letterSkin = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.letter_skin);
    }

    public void switchToDemo(int i) {
        switch (i) {
        // -1代表什么都不做
        case DEMO_NONE:
            break;

        case DEMO_MENU:
            setContentView(R.layout.cust_menu);
            break;
        }
    }
}
