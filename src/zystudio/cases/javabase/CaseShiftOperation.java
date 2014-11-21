package zystudio.cases.javabase;

import android.content.Context;
import android.util.Log;
import android.view.View.MeasureSpec;

/**
 * 移位操作Demo，Java中：
 * <ul>
 * <li>
 * <<是左移：左移的规则是 丢弃最高位，0补最低位;就是说，即使最高位是符号位，也要舍弃
 *
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

    public void work(){
//        colorShiftDemo();
        bitShiftMeasureDemo();
    }

    /**
     * 这个是我在做夜间模式的操作透明度变化时的实验，现在改变的两位就是aplha，已经被证明成功。
     * 当然argbvaluator里边的，好像更先进
     */
    public void colorShiftDemo() {
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
         MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST);
        // 这个就是-2^30，0x11左移30位后，符号位为1,最高数据位为1且后边有30个0
        Log.i("ertewu", "MODE_MASK:" + MODE_MASK + "\n");
        // 这个就是0啦，左移肯定都是0
        Log.i("ertewu", "UNSPECIFIED:" + UNSPECIFIED + "\n");
        // 这个就是正的2^30啦，完全能理解
        Log.i("ertewu", "EXACTLY:" + EXACTLY + "\n");
        // 这个就不好理解了，这个符号为是1,除此之后都是0了，但是这个数据是2^31，这也就是传说中的最高位省略
        Log.i("ertewu", "AT_MOST:" + AT_MOST + "\n");
        Log.i("ertewu", "---------------------------");
    }

    /** 那个这个并非shift方面的，这个是看到MeasureSpec的MODE_MASK这个字段，想了解一下使用方法*/
    public void bitMaskDemo() {
        /**
        * 让我们来看这么一种使用方法：makeMeasureSpec(int size,int mode) return的是size+mode; <br>
        * 而getMode()是 measureSpec&MODE_MASK <br>
        * 而getSize是 measureSpec&~MODE_MASK <br>
        * 这是一种怎么样的使用方式呢？下边这样来表示：
        * <ul>
        * <li>MASK: 11 00...(总共30个0)
        * <li>MODE EXACTLY:   01 00...(总共30个0)
        * <li>MODE AT_MOST: 10 00...(总共30个0)
        * <li>MODE UNSPECIFIED: 00 00...(总共30个0)
        * </ul>
        * 这样已经看得很清楚了，size的值在后30位中肯定够用了(即最大值是2^30-1); 而前两位专门用于记录MODE<br>
        * 当(size+mode)和mode进行“与”时：只得到了MODE
        * 当(size+mode)和mode进行反与时，只得到了size
        * 多么强大的信息记录方式
        */
    }

    /*************** argbevaluator Demo ****************/

    public void bitShiftARGBValuatorDemo() {

    }
}
