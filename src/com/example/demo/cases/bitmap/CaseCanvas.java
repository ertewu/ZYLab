package com.example.demo.cases.bitmap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.demo.R;

public class CaseCanvas {
    /**
     * 用于展示各种canvas使用的demo,目前为止是想摘一些GlowPadExample的效果的
     */

    private static CaseCanvas sCase;
    private Activity mAct;

    public static CaseCanvas obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseCanvas();
            sCase.mAct = act;
        }
        return sCase;
    }

    private CaseCanvas() {

    }

    public void work() {
        mAct.setContentView(new CanvasDemoView(mAct));
    }

    static class CanvasDemoView extends View {

        private Drawable mImage;
        private Rect mRect;
        private Paint mPaint;

        public CanvasDemoView(Context context) {
            this(context, null);
        }

        public CanvasDemoView(Context context, AttributeSet attrs) {
            super(context, attrs);
            mImage = getContext().getResources().getDrawable(
                    R.drawable.android_icon);
            mRect = new Rect(50, 50, 150, 150);
            mPaint = new Paint();
            mPaint.setColor(0xffff0000);
        }

        /**
         * 目前为止想的是试用canvas.save() canvas.restore <br>
         * 比如：canvas.save(Canvas.MATRIX_SAVE_FLAG)<br>
         * 以及之间使用的canvas.translate<br>
         * canvas.scale
         *
         */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // showDemo1(canvas);
            // showDemo2(canvas);
            // testSetBounds(canvas);
            // testCanvasAction(canvas);
        }


        private void testLayer(Canvas canvas) {

        }

        private void testCanvasAction(Canvas canvas) {
            Rect rect = mRect;

            // 旋转,rotate是有锚点的
            canvas.save();
            // 以右下角为中心轴，逆时间转了60度
            canvas.rotate(-30f, mRect.right, mRect.bottom);
            mImage.setBounds(rect);
            mImage.draw(canvas);
            mPaint.setColor(0x1aff0000);
            canvas.drawRect(mRect, mPaint);
            canvas.restore();

            // 试一试scale,果然是被拉伸了，scale也有锚点的
            canvas.save();
            canvas.scale(2, 3, 0, 0);
            mImage.setBounds(rect);
            mImage.draw(canvas);
            mPaint.setColor(0x1a00ff00);
            canvas.drawRect(mRect, mPaint);
            canvas.restore();

        }

        /**
         * 验证setBounds对于drawable的影响
         */
        private void testSetBounds(Canvas canvas) {
            // mImage的intrictWidth与intrictHeight都是75,按理说我设40的话，
            // 应该是显示不出来全部了,但事实是缩小了..
            Rect rect = mRect;
            mImage.setBounds(new Rect(0, 0, 40, 40));
            mImage.draw(canvas);

            // 这个还拉长了..看来setBounds会改变一个drawable的形状
            mImage.setBounds(new Rect(0, 100, 150, 150));
            mImage.draw(canvas);

            // 果然啊，这个变大了
            rect = new Rect(100, 200, 250, 350);
            mImage.setBounds(rect);
            mImage.draw(canvas);

            // 这种情况下，竟然是倒着的
            rect = new Rect(400, 200, 400 - mImage.getIntrinsicWidth(),
                    200 - mImage.getIntrinsicHeight());
            mImage.setBounds(rect);
            mImage.draw(canvas);

            // 这种是正常的
            rect = new Rect(50, 400, 50 + mImage.getIntrinsicWidth(),
                    400 + mImage.getIntrinsicHeight());
            mImage.setBounds(rect);
            mImage.draw(canvas);

        }

        /**
         * 这个是用颜色与drawable综合显示来作对比的,可以看到drawable.draw之前必须要进行setBounds，
         * 否则是完全画不出来什么的
         */
        private void showDemo2(Canvas canvas) {
            // mImage.draw(canvas);
            mImage.setBounds(mRect);
            mImage.draw(canvas);
            mPaint.setColor(0x6aff0000);
            canvas.drawRect(mRect, mPaint);

            // save
            canvas.save();
            canvas.translate(80, 80);
            mImage.draw(canvas);
            mPaint.setColor(0x6a00ff00);
            canvas.drawRect(mRect, mPaint);

            // restore后，canvas又回来了
            canvas.restore();
            mRect.offset(100, 100);
            mImage.setBounds(mRect);
            mImage.draw(canvas);
            mPaint.setColor(0x1a0000ff);
            canvas.drawRect(mRect, mPaint);
        }

        // 这个是用颜色的，我们可以用drawable来试试
        private void showDemo1(Canvas canvas) {
            // canvas没有作任何事情时
            canvas.drawRect(mRect, mPaint);
            // canvas 先save再作translate
            canvas.save();
            canvas.translate(80, 80);
            mPaint.setColor(0xff00ff00);
            canvas.drawRect(mRect, mPaint);
            // restore后，canvas又回来了
            canvas.restore();
            mRect.offset(100, 100);
            mPaint.setColor(0xff0000ff);
            canvas.drawRect(mRect, mPaint);
        }
    }

    private static void log(String str) {
        Log.i("ertewu", str);
    }
}
