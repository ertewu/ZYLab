package zystudio.cases.prepare;

import android.util.Log;

public class CaseCountDownLatch {

    private static CaseCountDownLatch sCase;
    //访viewgroup中的那个
    //基础类型 int 值不写，默认是0..
    //http://simon-c.iteye.com/blog/1016031
    //int 的最大值 (2^31)-1  最小值 -2^31
    private int mGroupFlags;

    public static CaseCountDownLatch obtain() {
        if (sCase == null) {
            sCase = new CaseCountDownLatch();
        }
        return sCase;
    }

    public void work(){
        final int FLAG_DISALLOW_INTERCEPT = 0x80000;
        int result=mGroupFlags&FLAG_DISALLOW_INTERCEPT;
        Log.i("ertewu", "mGroupFlags is:"+mGroupFlags);
        Log.i("ertewu", "result is:"+result);
    }

}
