package zystudio.demo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class MixColorFrameLayout extends FrameLayout {

    public MixColorFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawColor(0xffeeeeee,Mode.SRC_OUT);
    }
}
