package zystudio.demo.views;

import zystudio.demo.R;
import zystudio.mylib.utils.LogUtil;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class CustAttrView extends View {

    /*
     * 至于第三个参数即defStyle是干什么的，这个问题
     * http://www.cnblogs.com/angeldevil/p/3479431.html
     *  这里边有写,虽然目前我也不是很明白，这个最好自己试一试
     */
    public CustAttrView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LogUtil.log("CustAttrView three parameter constructor" + getResourceIdName() + "_defStyle:"
                + defStyle);
    }

    public CustAttrView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.log("CustAttrView two parameters constructor occured");
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustAttr, 0, 0);
        int location = ta.getInt(R.styleable.CustAttr_location, 4);
        // LogUtil.log("CustAttrView_"+getResourceIdName()+"_ location is:" +
        // location);
    }

    private String getResourceIdName() {
        return getResources().getResourceName(getId());
    }
}