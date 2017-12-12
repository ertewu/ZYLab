package zystudio.cases.javabase;

import java.util.Random;

import zystudio.mylib.utils.LogUtil;

/**
 *试验生成Random的随机数的方法
 */
public class CaseJavaRandom {

    private static CaseJavaRandom sCase;
    public static CaseJavaRandom obtain() {
        if (sCase == null) {
            sCase = new CaseJavaRandom();
        }
        return sCase;
    }

    public void work(){
        testJavaUtilRandom();
        LogUtil.log("-----------------------------------------");
        testThisIsFakeRandom();
    }

    private  void testJavaUtilRandom(){
        Random random=new Random();
        //random的构造函数是需要一个基值seed的，当用没有这个基值的函数时，将使用系统时间作为seed
        for(int i=0;i<10;i++){
            //这个意思是 需要一个[0,17)的均匀分布的int值
            int k=random.nextInt(17);
            LogUtil.log("testJavaUtilRandom:"+k);
        }
    }

    private void testThisIsFakeRandom() {

        Random random = new Random(256);
        // random的构造函数是需要一个基值seed的，当用没有这个基值的函数时，将使用系统时间作为seed
        for (int i = 0; i < 10; i++) {
            // 这个意思是 需要一个[0,17)的均匀分布的int值
            int k = random.nextInt(17);
            LogUtil.log("testThisIsFakeRandom 1:" + k);
        }
        LogUtil.log("-----------------------------------------");
        Random random2 = new Random(256);
        // random的构造函数是需要一个基值seed的，当用没有这个基值的函数时，将使用系统时间作为seed
        for (int i = 0; i < 10; i++) {
            // 这个意思是 需要一个[0,17)的均匀分布的int值
            int j = random2.nextInt(17);
            LogUtil.log("testThisIsFakeRandom 2:" + j);
        }

        //可以看出random1与random2的输出和顺序是一样样的...,所以是个伪随机数
    }

    private  void testMathRandom(){
        //Math.random只是一个函数，会返回一个 [ 0-1)之间的数，简单的使用就这个OK了
        //但实际上这个也是调用了java.util.Random的..，也就是说上一次方法
        for(int i=0;i<10;i++){
            LogUtil.log("testMathRandom:"+Math.random());
        }
    }
}
