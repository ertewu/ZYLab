package com.example.demo.cases.prepare;

import android.content.Context;
import android.util.Log;

/**
 * 移位操作Demo，Java中：
 * <ul>
 * <li>
 * <<是左移：左移的规则是 丢弃最高位，0补最低位
 * <li>
 * >>是右移:右移的规则是：低位舍弃，高位的空位补符号位；也就是如果符号位是0的话，那就补0,符号位是1的话，那就补1；
 * <li>
 * >>>是无符号右移：
 * </ul>
 * </p>当然这些东西都是针对于二进制的，所有的数都得先转为二进制 <br>
 * Refer From: http://java.chinaitlab.com/base/828314.html
 */
public class CaseShiftOperation {

    private static CaseShiftOperation sCase;

    public static CaseShiftOperation obtain(Context context) {
        if (sCase == null) {
            sCase = new CaseShiftOperation();
        }
        return sCase;
    }

    private CaseShiftOperation() {

    }

    /**
     * 这个是我在做夜间模式的操作透明度变化时的实验，现在改变的两位就是aplha，已经被证明成功。
     * 当然argbvaluator里边的，好像更先进
     */
    public void work() {
        int color = 43;
        int leftshiftValue = color << 24;
        int multiplyValue = color * 0x01000000;

        // 这两个值是一样的,这个打出来的是10进制的数
        Log.i("ertewu", "r28:" + leftshiftValue + "|" + multiplyValue);
    }

    /**
     * 在现实的例子中，有好多左移与右移的应用，比如： <br/>
     * MeasureSpec 类<br>
     * argbevaluator的类中的颜色的移位<br>
     * 还有我让改变颜色aphla那个24移位</br> 我想知道那些东西是怎么运作的
     */

    /***************** MeasureSpec Demo *****************/

    final int MODE_SHIFT = 30;
    final int MODE_MASK = 0x3 << MODE_SHIFT;
    final int UNSPECIFIED = 0 << MODE_SHIFT;
    final int EXACTLY = 1 << MODE_SHIFT;
    final int AT_MOST = 2 << MODE_SHIFT;

    /** 这个是找MeasureSpec的shift移位 */
    public void bitShiftMeasureDemo() {
        // MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST);
    }

    /** 那个这个并非shift方面的，这个是看到MeasureSpec的MODE_MASK这个字段，想了解一下使用方法 */
    public void bitMaskDemo() {

    }

    /*************** argbevaluator Demo ****************/

    public void bitShiftARGBValuatorDemo() {

    }
}
