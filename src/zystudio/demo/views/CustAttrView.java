package zystudio.demo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import zystudio.demo.R;

public  class CustAttrView extends View {

    public CustAttrView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.CustAttr, 0, 0);
        int location=ta.getInt(R.styleable.CustAttr_location, 4);
        Log.i("ertewu", "location is:"+location);
    }
}