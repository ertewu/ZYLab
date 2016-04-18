package zystudio.demo.views;

import zystudio.demo.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <ol>
 * <li>在这个demo中 ， 学会了Drawable.setBounds的用法以及参数的意义
 * <li>
 * 学会了Canvas.drawBitmap(Bitmap src,Rect srcRect,Rect dstRect,Paint
 * paint)的用法以及参数的意义，真是挺宝贵的，还是先留着吧..
 * </ol>
 */

public class MenuView2 extends View {

    private static final int DEFAULT_PAINT_FLAGS = Paint.FILTER_BITMAP_FLAG
            | Paint.DITHER_FLAG;
    private static final String STR = "收藏/历史";
    private static int NUM = 8;
    private static int TEXTSIZE = 20;

    private int mIconWidth;
    private int mIconHeight;

    private Drawable mIcon;
    private Bitmap mBitmap;

    private Paint mIconPaint;
    private Rect mSrcRect, mDstRect;

    public MenuView2(Context context) {
        super(context);
    }

    public MenuView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mIcon = getResources().getDrawable(R.drawable.menu_skin);
        mIconWidth = mIcon.getIntrinsicWidth();
        mIconHeight = mIcon.getIntrinsicHeight();

        Rect intrinsicRect = new Rect(0, 0, mIconWidth, mIconHeight);
        // mIcon.setBounds(intrinsicRect);

        // 这个函数也要试一下
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.menu_skin);
        mIconPaint = new Paint(DEFAULT_PAINT_FLAGS);
        mSrcRect = new Rect();
        mDstRect = new Rect();

        mSrcRect.set(0, 0, mIconWidth / 2, mIconHeight / 2);
        mDstRect.set(10, 10, mIconWidth, mIconHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 先画背景颜色为红色
        canvas.drawARGB(255, 255, 0, 0);
        // mIcon.draw(canvas);
        canvas.drawBitmap(mBitmap, mSrcRect, mDstRect, null);
    }

    private class MenuPos {
        public int mLeft;
        public int mTop;

        public MenuPos(int left, int top) {
            mLeft = left;
            mTop = top;
        }
    }
}
