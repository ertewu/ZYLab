package zystudio.cases.androidmechanism;

import android.app.Activity;

/**
 * Created by wzy on 14-12-2.
 * 其实这是一个计算方面的Case，主要想弄清Window.java里的
 * clearFlags
 * addFlags
 * 的用法，其实这两个函数都是调用setFlags的，所以主要也是看setFlag的位操作的方法,我把这些源码写在这里
 */
public class CaseWindowFlagOperate {
    private static CaseWindowFlagOperate sInstance;
    private Activity mActivity;

    private CaseWindowFlagOperate() {

    }

    public static CaseWindowFlagOperate obtain(Activity activity) {
        if (sInstance == null) {
            sInstance = new CaseWindowFlagOperate();
        }
        return sInstance;
    }


    public void work() {

    }

    //看clearFlags中这句话是怎么用的 attrs.flags = (attrs.flags&~mask) | (flags&mask);
    private void testClearFlags() {
        /**
         * public void clearFlags(int flags) {setFlags(0, flags);}
         * 显然flag设为了0，但是mask却设成了以前的flag
         * 那么flags&mask==0,|是按位或的话，则value只能指望attrs.flags&~mask了，那么分析这个东西：
         * ~mask就是把这个flag的非1的置0,0的都置1
         * 那么，attrs.flags&了这个值的话，其实就是把这个flag使用的位都清0了，而其它位不变
         *
         * 主要是十进制太麻烦了，如果写成二进制的形式就清楚多了..但是java没有int的二进制表示
         */
    }

    //主要是看这句话是怎么用的 attrs.flags = (attrs.flags&~mask) | (flags&mask);
    private void testAddFlags() {
        //    public void addFlags(int flags) {setFlags(flags, flags);    }
        /*
         * 继续分析,flags和mask完全相同，所以flags&mask实际就是flags
         * 于是就变成了(attrs.flags&~flag)|flag
         * attrs.flags&~flag实际上还是把 当前flag所用到的那几个二制位清0了
         * 再|flag之后，实际上就是把flag所用的那几个二进制位置1了，
         * 这里如何保证attrs.flags其它位不变？其实只能有赖于flag的结构
         * ，那就是不用的二进制位肯定是0，那么attrs.flags或0的时候就和没变一样了
         */
        /**
         * java的int 是32位整数，除一个符号位的话，就是31位，从这个角度说最多有31个flag,实际上Window中用了多少个呢？
         * WindowManager.LayoutParams中，用“public static final int FLAG” 这个字符串搜的话，有31个，正好是这样子的
         * 最高的0x80000000,是没有设为flag的
         */
    }





//    public void setFlags(int flags, int mask) {
//        final WindowManager.LayoutParams attrs = getAttributes();
//        attrs.flags = (attrs.flags&~mask) | (flags&mask);
//        if ((mask&WindowManager.LayoutParams.FLAG_NEEDS_MENU_KEY) != 0) {
//            attrs.privateFlags |= WindowManager.LayoutParams.PRIVATE_FLAG_SET_NEEDS_MENU_KEY;
//        }
//        mForcedWindowFlags |= mask;
//        if (mCallback != null) {
//            mCallback.onWindowAttributesChanged(attrs);
//        }
//    }

}
