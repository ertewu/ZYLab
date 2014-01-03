package com.example.demo.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AnimImageView extends ImageView {

	private Paint mMaskPaint;
	private Rect mMaskRect;

	public static int MASK_MIN_LEVEL_COLOR = 0xff;
	public static int MASK_MAX_LEVEL_COLOR = 0;
	// 10进制100
	public static int BRIGHTNESS_MAX_RATE = 100;

	private int mMaskRate;
	private int mMaskColor;

	public AnimImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mMaskPaint = new Paint();
		mMaskRect = new Rect();
	}

	// 这个给个0-100的透明蒙版，0是全透明，即显示原图片
	public void setAlphaMaskRate(int rate) {
		mMaskRate = rate;
		convertMaskRateToColor();
		invalidate();
	}

	public int getAlphaMaskRate() {
		return mMaskRate;
	}

	private void convertMaskRateToColor() {
		int color = MASK_MIN_LEVEL_COLOR - MASK_MIN_LEVEL_COLOR * mMaskRate
				/ BRIGHTNESS_MAX_RATE;
		mMaskColor = color << 24;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		getDrawingRect(mMaskRect);
		mMaskPaint.setColor(mMaskColor);
		canvas.drawRect(mMaskRect, mMaskPaint);
	}

	public void displayAnim(){
	    ValueAnimator animation = ValueAnimator.ofInt(100,30);
	    animation.setDuration(1000);
	    animation.addUpdateListener(new AnimatorUpdateListener() {
	        @Override
	        public void onAnimationUpdate(ValueAnimator animation) {
	            int rate=(Integer) animation.getAnimatedValue();
	            setAlphaMaskRate(rate);
	        }
	    });
	    animation.start();
	}
}
