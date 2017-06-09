package zystudio.cases.javabase;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import zystudio.mylib.utils.LogUtil;

/**
 * 这里还试试Android的MemoryInfo类
 */
public class CaseOOMErrorCatch {

    private static CaseOOMErrorCatch sCase;
    private Context mContext;
    private static final int MEGABYTE = (1024 * 1024);
    private ArrayList<BytesBean> mBeans;

    public static CaseOOMErrorCatch obtain(Context context) {
        if (sCase == null) {
            sCase = new CaseOOMErrorCatch();
        }
        sCase.mContext = context;
        return sCase;
    }

    private CaseOOMErrorCatch() {
        mBeans = new ArrayList<BytesBean>();
    }

    public void work() {
        try {
            // runOutOfMemoryByteArray();
            runOutOfMemoryBitmap();
        } catch (Throwable myThrow) {
            LogUtil.log("Work throwable occured:" + myThrow.getMessage());
        }
    }

    /**
     * 从这个结果来看,当真是被catch住了的...,起码byte数组是被catch住了
     */
    private void runOutOfMemoryByteArray() {
        for (int i = 1; i <= 100; i++) {
            try {
                byte[] bytes = new byte[MEGABYTE * 5000];
                BytesBean bean = new BytesBean(bytes);
                mBeans.add(bean);
            } catch (Exception e) {
                LogUtil.log("Exception ouucred:" + e.getMessage());
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                LogUtil.log("OutOfMemoryCatched:" + System.currentTimeMillis() + "|" + i);
            }
        }
    }

    private void runOutOfMemoryBitmap() {
        ArrayList<Bitmap> myBitmaps = new ArrayList<Bitmap>();
        int i = 0;
        while (i < 100) {
            try {
                // Bitmap myBitmap =
                // BitmapFactory.decodeFile("/sdcard/0_MyDir/china_map.jpg");
                Bitmap myBitmap = Bitmap.createBitmap(10000, 10000, Bitmap.Config.ARGB_8888);
                LogUtil.log("index is:" + i);
                myBitmaps.add(myBitmap);
                i++;
                LogUtil.log("myBitmap occured:" + myBitmap.getHeight() + "|" + myBitmap.getWidth());
            } catch (OutOfMemoryError err) {
                LogUtil.log("runOutOfMemoryBitmap  oom:" + err.getMessage());
            }
        }

    }

    public static class BytesBean {
        private byte[] mbytes;

        public BytesBean(byte[] bytes) {
            mbytes = bytes;
        }
    }
}
