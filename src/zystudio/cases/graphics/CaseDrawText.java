package zystudio.cases.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class CaseDrawText {
    /**
     * 用于展示各种canvas使用的demo,目前为止是想摘一些GlowPadExample的效果的
     */

    private static CaseDrawText sCase;
    private Activity mAct;

    public static CaseDrawText obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseDrawText();
            sCase.mAct = act;
        }
        return sCase;
    }

    private CaseDrawText() {

    }

    public void work() {
        mAct.setContentView(new DrawTextView(mAct));
    }

    private static class DrawTextView extends View {

        private static final int TEXT_SIZE = 65;
        private Paint textPaint, baseLinePaint, topLinePaint, ascentLinePaint, descentLinePaint,
                bottomLinePaint;
        // private String text = "abcdefghijklmnopqrstu";
        private String text = "你好明朝那些，事儿";
        Rect commaBounds = new Rect();

        public DrawTextView(Context context) {
            super(context);
            init();
        }

        private void init() {
            textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            baseLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            topLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            ascentLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            descentLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            bottomLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawHorizontalSpacingText(canvas);
//            draw1Demo(canvas);
//            draw2Demo(canvas);
        }
        private void drawHorizontalSpacingText(Canvas canvas){
            String text="明朝那些事儿，你还好？我还行";
            int spacing=10;
            int textSize=65;
            Paint paint=new  Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(textSize);
            int baseX=50;
            int baseY=100;
            final int count=text.length();
            float[] pos=new float[count*2] ;
            int iterPos=baseX;
            for(int i=0;i<count;i++){
                pos[i*2]=baseX+i*spacing+i*textSize;
                pos[i*2+1]=baseY;
            }
            canvas.drawPosText(text, pos, paint);
        }

        private void draw1Demo(Canvas canvas) {
            textPaint.setTextSize(TEXT_SIZE);
            textPaint.setColor(Color.WHITE);
            // FontMetrics对象
            FontMetrics fontMetrics = textPaint.getFontMetrics();
            // 计算每一个坐标
            float baseX = 0;
            float baseY = 100;
            float topY = baseY + fontMetrics.top;
            float ascentY = baseY + fontMetrics.ascent;
            float descentY = baseY + fontMetrics.descent;
            float bottomY = baseY + fontMetrics.bottom;
            // 得到全角comma的bounds
            textPaint.getTextBounds(text, 0, 7, commaBounds);

            // 绘制文本
            canvas.drawText(text, baseX, baseY, textPaint);

            canvas.save();
            canvas.translate(baseX, baseY);
            textPaint.setColor(Color.RED);
            textPaint.setStyle(Style.STROKE);
            textPaint.setStrokeWidth(3);
            canvas.drawRect(commaBounds, textPaint);
            textPaint.getTextBounds(text, 0, 6, commaBounds);
            textPaint.setColor(Color.BLUE);
            canvas.drawRect(commaBounds, textPaint);
            textPaint.getTextBounds(text, 0, 8, commaBounds);
            textPaint.setColor(Color.BLUE);
            canvas.drawRect(commaBounds, textPaint);
            canvas.restore();

            // BaseLine描画
            baseLinePaint.setColor(Color.RED);
            canvas.drawLine(0, baseY, getWidth(), baseY, baseLinePaint);
            // Base描画
            canvas.drawCircle(baseX, baseY, 5, baseLinePaint);
            // TopLine描画
            topLinePaint.setColor(Color.LTGRAY);
            canvas.drawLine(0, topY, getWidth(), topY, topLinePaint);
            // AscentLine描画
            ascentLinePaint.setColor(Color.GREEN);
            canvas.drawLine(0, ascentY, getWidth(), ascentY, ascentLinePaint);
            // DescentLine描画
            descentLinePaint.setColor(Color.YELLOW);
            canvas.drawLine(0, descentY, getWidth(), descentY, descentLinePaint);
            // ButtomLine描画
            bottomLinePaint.setColor(Color.MAGENTA);
            canvas.drawLine(0, bottomY, getWidth(), bottomY, bottomLinePaint);
        }

        private void draw2Demo(Canvas canvas) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStrokeWidth(3);
            paint.setTextSize(80);
            FontMetricsInt fmi = paint.getFontMetricsInt();
            String testString = "测试：ijkJQKA:1234";
            Rect bounds1 = new Rect();
            paint.getTextBounds("测", 0, 1, bounds1);
            Rect bounds2 = new Rect();
            paint.getTextBounds("测试：ijk", 0, 6, bounds2);
            // 随意设一个位置作为baseline
            int x = 200;
            int y = 400;

            Paint pointPaint=new Paint();
            pointPaint.setColor(Color.RED);
            pointPaint.setStyle(Style.FILL);
            pointPaint.setStrokeWidth(10.0f);
            canvas.drawPoint(x, y, pointPaint);

            // 把testString画在baseline上
            canvas.drawText(testString, x, y, paint);
            // bounds1
            paint.setStyle(Style.STROKE); // 画空心矩形
            canvas.save();
            canvas.translate(x, y); // 注意这里有translate。getTextBounds得到的矩形也是以baseline为基准的
            paint.setColor(Color.GREEN);
            canvas.drawRect(bounds1, paint);
            canvas.restore();
            // bounds2
            canvas.save();
            paint.setColor(Color.MAGENTA);
            canvas.translate(x, y);
            canvas.drawRect(bounds2, paint);
            canvas.restore();
            // baseline
            paint.setColor(Color.RED);
            canvas.drawLine(x, y, 1024, y, paint);
            // ascent
            paint.setColor(Color.YELLOW);
            canvas.drawLine(x, y + fmi.ascent, 1024, y + fmi.ascent, paint);
            // descent
            paint.setColor(Color.BLUE);
            canvas.drawLine(x, y + fmi.descent, 1024, y + fmi.descent, paint);
            // top
            paint.setColor(Color.DKGRAY);
            canvas.drawLine(x, y + fmi.top, 1024, y + fmi.top, paint);
            // bottom
            paint.setColor(Color.GREEN);
            canvas.drawLine(x, y + fmi.bottom, 1024, y + fmi.bottom, paint);
        }
    }

    private static void log(String str) {
        Log.i("ertewu", str);
    }
}
