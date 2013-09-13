package com.example.demo.cases;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.demo.R;

public class CaseForDraw {

    private static CaseForDraw sCase;
    private Activity mAct;

    public static CaseForDraw getInstance(Activity activity) {
        if (sCase == null) {
            sCase = new CaseForDraw();
        }
        sCase.init(activity);
        return sCase;
    }

    private void init(Activity act){
        mAct=act;
    }


    public void work(){
        mAct.setContentView(new CustView(mAct));
    }

    private static class CustView extends View{

        private Paint mTextPaint;
        public CustView(Context context){
            super(context,null);
            init();
        }

        public CustView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }
        private void init(){

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawText(canvas);
            drawBitmap(canvas);
        }
        
        
        //用TextPaint类画文字，并对比与Paint类相对，这个有什么优势
        private void drawByTextPaint(Canvas canvas){
        	
        }

        private void drawText(Canvas canvas){
            mTextPaint=new Paint();
            //这个效果，我没有看出太明显差别来
            mTextPaint.setAntiAlias(true);
            //这个是像素
            mTextPaint.setTextSize(50);

            //文字这边的坐标，好像完全不一样，颠覆着来的
            //找一些参考： http://blog.csdn.net/lvxiangan/article/details/8540774

            //setARGB和setColor在本质上是一个函数的调用
            mTextPaint.setColor(android.graphics.Color.RED);
            //我不知道这个是去Align谁的center，但是这个变化挺大的，上边那个网址也有解释了
            //这个是指的一段文字的Align..而不是单个文字
            //字符 baseline 是什么概念？
            mTextPaint.setTextAlign(Paint.Align.LEFT);

            //字是从右向左画的？这个文字的，算不出来坐标是怎么回事，底下那个示例更能反应问题
            canvas.drawText("好啊", 100, 100, mTextPaint);
//            canvas.drawText("好啊一二", 100, 100, mTextPaint);
        }

        private void drawBitmap(Canvas canvas){
            //这个Paint不需要设置些什么东西？
            Paint myPaint=new Paint();
            Bitmap checkon=BitmapFactory.decodeResource(getResources(), R.drawable.checkon);
            //X轴的372=480-108(108是图片的宽)
            //显示出来的图片 上边高度就是200
            canvas.drawBitmap(checkon,372,200,myPaint);
            //这个是作重合的样子，到时我做的那个滑动按钮是有图片重合的
//            canvas.drawBitmap(checkon,372,220,myPaint);
        }
    }

    /*
     * 仿照InteractiveChart,我可以一点一点的画:
     *   先画一下那个文字
     *   再画一下，四周的边框
     *   读一张位图，然后再画到Canvas上
     *
     *   其中最重要的是掌握painter与canvas的用法
     * */
}
