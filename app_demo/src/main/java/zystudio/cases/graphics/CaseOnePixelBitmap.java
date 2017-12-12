package zystudio.cases.graphics;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

import zystudio.demo.R;

/**
 * Created by wangzhengyu on 2017/10/27.
 */

public class CaseOnePixelBitmap {

    private static CaseOnePixelBitmap sCase;
    private static Bitmap sOnePixelBitmap=null;

    private Activity mActivity;

    public static CaseOnePixelBitmap obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseOnePixelBitmap();
            sCase.mActivity=act;
        }
        return sCase;
    }

    public void work(){
        mActivity.setContentView(R.layout.case_pixelbitmap);
        ImageView imgView=(ImageView) mActivity.findViewById(R.id.onepixel_iv);
        Bitmap onePixelBitmap=onePixelBitmap();
        imgView.setImageBitmap(onePixelBitmap);
    }


    //是成功了的，就是这个
    public static Bitmap onePixelBitmap(){
        if(sOnePixelBitmap==null || sOnePixelBitmap.isRecycled()){
            final int width=1;
            final int height=1;
            sOnePixelBitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            //pure white pixel
            int color=0xffffffff;
            sOnePixelBitmap.setPixel(0,0,color);
        }
        return sOnePixelBitmap;
    }
}
