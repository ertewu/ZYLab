package zystudio.demo.views;

import zystudio.demo.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MenuView extends View {

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
    private Paint mTextPaint;
    private Rect mSrcRect, mDstRect;

    private Rect mTextRect;

    public MenuView(Context context) {
        super(context);
    }

    public MenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyle) {
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
        mTextRect = new Rect();

        mSrcRect.set(0, 0, mIconWidth, mIconHeight);
        mDstRect.set(10, 10, mIconWidth, mIconHeight);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(android.graphics.Color.WHITE);
        mTextPaint.setTextSize(30);
        mTextPaint.setTextAlign(Align.LEFT);
        mTextPaint.getTextBounds(STR, 0, STR.length(), mTextRect);
        Log.i("ertewu", mTextRect.toString());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 先画背景颜色为红色
        canvas.drawARGB(255, 255, 0, 0);
        // mIcon.draw(canvas);
        canvas.drawBitmap(mBitmap, mSrcRect, mDstRect, null);
        canvas.drawText(STR, mIconWidth / 2 -mTextRect.width() / 2,
                mIconHeight + 10 - mTextRect.top, mTextPaint);
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
