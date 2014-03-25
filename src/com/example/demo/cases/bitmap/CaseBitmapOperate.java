package com.example.demo.cases.bitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.example.demo.R;

/**
 * 主要是去辩别BitmapFactory.Options 中的<br>
 * opt.inDensity;<br>
 * opt.inScreenDensity<br>
 * opt.inTargetDensity<br>
 * 这三个的功能，以及怎么用
 * 
 * 参考有： http://blog.csdn.net/sevensundark/article/details/7616450
 */
public class CaseBitmapOperate {

    private static CaseBitmapOperate sCase;
    private Activity mActivity;

    public static CaseBitmapOperate obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseBitmapOperate();
            sCase.init(act);
        }
        return sCase;
    }

    private void init(Activity activity) {
        this.mActivity = activity;
    }

    public void work() {
        testDensitySmallBitmap();
        // testDensity();
        // testInDensityMultiDpi();
    }

    /**
     * 这个主要是为了看不同的mTargetDensity下的有什么不同
     */
    private void testDensitySmallBitmap() {
        BitmapFactory.Options opt = new BitmapFactory.Options();

        // 原始例子
        Bitmap img1 = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.android_icon, opt);

        Log.i("ertewu",
                opt.inDensity + "|" + opt.inTargetDensity + "|"
                        + img1.getDensity() + "|" + img1.getWidth() + "|"
                        + img1.getHeight());

        opt.inDensity = 80;
        // opt.inTargetDensity = 160;
        Bitmap img2 = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.android_icon, opt);

        Log.i("ertewu",
                opt.inDensity + "|" + opt.inTargetDensity + "|"
                        + img2.getDensity() + "|" + img2.getWidth() + "|"
                        + img2.getHeight());

        opt.inDensity = 160;
        // opt.inTargetDensity = 320;
        Bitmap img3 = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.android_icon, opt);

        Log.i("ertewu",
                opt.inDensity + "|" + opt.inTargetDensity + "|"
                        + img3.getDensity() + "|" + img3.getWidth() + "|"
                        + img3.getHeight());

        // 这个例子没有得到什么特殊的东西
        opt.inDensity = 80;
        opt.inTargetDensity = 40;
        opt.inScaled = true;

        Bitmap img4 = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.android_icon, opt);

        Log.i("ertewu",
                opt.inDensity + "|" + opt.inTargetDensity + "|"
                        + img4.getDensity() + "|" + img4.getWidth() + "|"
                        + img4.getHeight());

        showBitmap(img1, img2, img3, img4);

        /**
         * s2:<br>
         * 0|240|240|72|72<br>
         * 80|240|240|216|216<br>
         * 160|240|240|108|108<br>
         * 80|40|40|36|36<br>
         *  第四个我们这样想：40inTargetDensity的宽为36,那在240inTargetDensity下显示
         *  就相当于240下6个density单位才相当于一个40下的1个density单位，于是大小也是216，但是就显示粗糙了..
         * 虽然第四个与第二个数是不一样的，但是显示却是一样大小的，但是有细节有区别..第四个不清楚很多啊，这两个在s2上都显示为216px的宽高
         * 
         * nexus 7:<br>
         * 0|320|320|72|72<br>
         * 80|320|320|288|288<br>
         * 160|320|320|144|144<br>
         */
    }

    /**
     * 这个只是试一下
     */
    private void testDensity() {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        Bitmap bit = null;
        bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_nodpi, opt);
        // 这个是很原始的东西了：inDensity:0,在nexu7上，mTargetDensity是320,
        // 然后宽高是：1600,1200，很原始的
        Log.i("ertewu",
                "moto  inDensity:" + opt.inDensity + "|" + bit.getDensity()
                        + "|" + bit.getWidth() + "|" + bit.getHeight());
        bit.recycle();
        /***************************************************************/
        opt.inDensity = 160;
        opt.inTargetDensity = 320;
        bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_nodpi, opt);
        // 这个打出来的宽与高是:3200,2400
        Log.i("ertewu",
                "moto  inDensity:" + opt.inDensity + "|" + bit.getDensity()
                        + "|" + bit.getWidth() + "|" + bit.getHeight());
        bit.recycle();

        /***************************************************************/
        opt.inDensity = 160;
        opt.inTargetDensity = 240;
        bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_nodpi, opt);
        // 这个打出的宽高是：2400,1800
        Log.i("ertewu",
                "moto  inDensity:" + opt.inDensity + "|" + bit.getDensity()
                        + "|" + bit.getWidth() + "|" + bit.getHeight());
        bit.recycle();

        /***************************************************************/
        opt.inDensity = 160;
        opt.inTargetDensity = 160;
        bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_nodpi, opt);
        // 这个打出的宽高是：1600,1200
        Log.i("ertewu",
                "moto  inDensity:" + opt.inDensity + "|" + bit.getDensity()
                        + "|" + bit.getWidth() + "|" + bit.getHeight());
        bit.recycle();
        /**
         * 如此说来，其实位图的大小是不一定的..
         */

        /***************************************************************/
        opt.inDensity = 120;
        opt.inTargetDensity = 360;
        bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_nodpi, opt);
        // 这个打出的宽高是：4800,3600
        Log.i("ertewu",
                "moto  inDensity:" + opt.inDensity + "|" + bit.getDensity()
                        + "|" + bit.getWidth() + "|" + bit.getHeight());
        bit.recycle();
    }

    /**
     * 
     * 实验case 是这个函数要在不同分辩率的手机上跑:
     * <ol>
     * <li>在我的s2上，所有都是：120|240
     * <li>在n7 上，打出的都是：120|320
     * </ol>
     * 也就是说，bit在不同的手机上生成，density是不同的？默认是屏幕的分辩率~
     */
    private void testInDensityMultiDpi() {
        BitmapFactory.Options opt = new BitmapFactory.Options();

        Bitmap bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_ldpi, opt);
        Log.i("ertewu",
                "moto ldpi inDensity:" + opt.inDensity + "|" + bit.getDensity());
        bit.recycle();

        bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_hdpi, opt);
        Log.i("ertewu",
                "moto hdpi inDensity:" + opt.inDensity + "|" + bit.getDensity());
        bit.recycle();
        bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_xhdpi, opt);
        Log.i("ertewu",
                "moto xhdpi inDensity:" + opt.inDensity + "|"
                        + bit.getDensity());
        bit.recycle();

        bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor_nodpi, opt);
        Log.i("ertewu",
                "moto nodpi inDensity:" + opt.inDensity + "|"
                        + bit.getDensity());
        bit.recycle();

    }

    // 这函数有问题啊..
    private void showBitmap(Bitmap... bitmaps) {
        LinearLayout rootLayout = (LinearLayout) mActivity.getLayoutInflater()
                .inflate(R.layout.showbitmap, null);
        for (Bitmap bitmap : bitmaps) {
            ImageView view = new ImageView(mActivity);
            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            view.setScaleType(ScaleType.FIT_XY);
            view.setImageBitmap(bitmap);
            view.setBackgroundColor(android.graphics.Color.RED);
            rootLayout.addView(view);
        }
        mActivity.setContentView(rootLayout);
    }
}
