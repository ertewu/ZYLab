package zystudio.demo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import zystudio.demo.R;

public class TestMeasureInfoView extends FrameLayout {

    private Bitmap mBitmap;
    private Rect mSrcRect;
    private Rect mDstRect;
    private BitmapDrawable mDrawable;

    public TestMeasureInfoView(Context context) {
        super(context, null);
        init();
    }

    public TestMeasureInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.motor);
        mBitmap = mDrawable.getBitmap();
        mSrcRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mDstRect = new Rect(0, 0, 480, 690);
        Log.i("ertewu", mBitmap.getWidth() + "|" + mBitmap.getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 从这里看出setContentView的measureSpec实际上是exactly的类型，是很直白的
         * 不过自设大小还是可以使用的
         */
        logMeasureSpec(widthMeasureSpec, "WidthMeasureSpec");
        logMeasureSpec(heightMeasureSpec, "heightMeasureSpec");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 这里总是画不出来位图呢，奇了个怪
        // canvas.drawBitmap(mBitmap, mSrcRect, mDstRect, null);
        // mDrawable.draw(canvas);

    }

    private void logMeasureSpec(int measureSpec, String title) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        String modeStr = null;
        switch (mode) {
        case MeasureSpec.AT_MOST:
            modeStr = "AT_MOST";
            break;
        case MeasureSpec.EXACTLY:
            modeStr = "EXACTY";
            break;
        case MeasureSpec.UNSPECIFIED:
            modeStr = "UNSPECIFIED";
            break;
        }

        Log.i("ertewu", title + " mode is:" + modeStr + "|size is:" + size);
    }

}
