package zystudio.cases.javabase;

import android.app.Activity;

/**
 * Created by wzy on 14-12-2.
 * 这个是用来深入理解Object的equals与hashCode的重写原理之类的
 * 我用得不多，但是在AbsSkinItem.java里有用过
 * 下边是一些可以参考的网址
 * http://developer.android.com/reference/java/lang/Object.html
 * http://developmentality.wordpress.com/2010/08/24/java-creating-correct-equals-and-hashcode-method/
 * http://en.wikipedia.org/wiki/Java_hashCode()
 * http://blog.csdn.net/fenglibing/article/details/8905007
 */
public class CaseObjEqualAndHashCode {

    private static CaseObjEqualAndHashCode sInstance;
    private Activity mActivity;

    public static CaseObjEqualAndHashCode obtain(Activity activity) {
        if (sInstance == null) {
            sInstance = new CaseObjEqualAndHashCode();
        }
        sInstance.mActivity = activity;
        return sInstance;
    }

    private CaseObjEqualAndHashCode() {

    }

    public void work(){
        testHashCode();
        testEquals();
    }

    private  void testHashCode(){
        /**
         * hashcode到底什么用？一直以来我也不太清楚，以前的大体感觉就是hashcode是一个整数，不同的obj的hashcode应该一定不同吧..就是这个意思
         * 首先来看看Object.java中的hashCode的annotation的说法（这注释是我从源码中看的）：
         *  “
         *  按照契约，两个用equal函数比较为true的obj,必须返回相同的hashcode.这也意味着Object类的子类一般是hashcode与equals丙个函数
         *  一起重写，或者都不重写
         *  并且，这个hashvalue肯定不随着时间改变（除非equals也能一样改变）
         *  ”
         *  至于系统的实现方法，我并看不懂，但是有提示write a correct hashcode的document
         *   http://developer.android.com/reference/java/lang/Object.html 这里边也有,但还是要解释一下什么的：
         *   我发现这么一句话：
         *   If you don't intend your type to be used as a hash key, don't simply rely on the default hashCode implementation
         *   也就是说,hashcode的初衷是为一个obj提供一个key
         *   显然上边的网址也不详细：
         */
    }

    private void testEquals(){
        /**
         * Android的源码中,Object的源码在
         * ./libcore/libart/src/main/java/java/lang/Object.java这个地点中
         * equal的函数源码就是：
         *     public boolean equals(Object o) {return this == o;}
         * 但是当我们写bean的时候，经常需要根据某个bean的核心值判断相等，比如我的皮肤，只要index相同，则视为相等
         * 这样就重写equals函数，并使用equals函数了（因此我们也知道,equals可以重写，但是==一直表示是同一个obj,不可被改）
         * 这个的典型例子就是java.lang.String对equal的重写,可以看出一些思路：
         *   1、判断==，即是否是同一个object
         *   2、判断类型
         *   3、这时，大概才是具体类型的自定义，我看到那些判断如下：
         *      1、判断count
         *      2、判断了hashcode
         *      3、这时才逐个判断那些字符是不是一样
         *
         * 其实我们也可以走这样的思路,并且
         * http://developer.android.com/reference/java/lang/Object.html
         * 这个文章里给了一个写equals的style..
         * 而且equals这个重写，系统会有推荐一部分的样子
         */
//     java.lang.String中的equals
//    @Override public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if (other instanceof String) {
//            String s = (String)other;
//            int count = this.count;
//            if (s.count != count) {
//                return false;
//            }
//            // TODO: we want to avoid many boundchecks in the loop below
//            // for long Strings until we have array equality intrinsic.
//            // Bad benchmarks just push .equals without first getting a
//            // hashCode hit (unlike real world use in a Hashtable). Filter
//            // out these long strings here. When we get the array equality
//            // intrinsic then remove this use of hashCode.
//            if (hashCode() != s.hashCode()) {
//                return false;
//            }
//            char[] value1 = value;
//            int offset1 = offset;
//            char[] value2 = s.value;
//            int offset2 = s.offset;
//            for (int end = offset1 + count; offset1 < end; ) {
//                if (value1[offset1] != value2[offset2]) {
//                    return false;
//                }
//                offset1++;
//                offset2++;
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }
    }


}
