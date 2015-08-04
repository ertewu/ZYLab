package zystudio.cases.javabase;

import android.util.Log;

public class CaseAddAdd {

    public static void work() {
        showDifficultCase();
        Log.i("ZYStudio", "-----------------");
//        showBaseCase();
//        Log.i("ZYStudio", "-----------------");
//        showLoopDemo();
    }

    //http://javaconceptoftheday.com/tricky-core-java-interview-coding-questions/    
    //from that site
    private  static void showDifficultCase(){
        A aCase=new A();
        
    }
    
    public static class A{
       static int i=1111;
       static {
           int k=i-- - i;
           Log.i("ZYStudio", "r23 i is:"+i+"|"+k);
           /**
            * 答案是1110|1, 是这样算的，先把第一次i换成1111 ，然后i-- 后i变成了1110, 然后-i 就算成了 -1110 ,这样子int k=1111-1110, 为k为1. i为1110, 
            * 看来这个i--是在把当前i赋值之后立即变的，甚至是在运算-之前的,所以后边的i已经变为1110了
            */
       }
    }
    
    private static void showBaseCase() {

        int i = 5;
        Log.i("ZYStudio", "r10:" + i++);// 5
        Log.i("ZYStudio", "r11 :" + i);// 6

        int k = 5;
        Log.i("ZYStudio", "r14:" + ++k);// 6
        Log.i("ZYStudio", "r15 :" + k);// 6

        int j = 5;
        j++;
        Log.i("ZYStudio", "r19 :" + j);

        int g = 5;
        ++g;
        Log.i("ZYStudio", "r23:" + g);
    }

    private static void showLoopDemo() {
        for (int i = 1; i < 3; i++) {
            Log.i("ZYStudio", "loop i:" + i);
        }
        Log.i("ZYStudio", "-----------------");
        for (int j = 1; j < 3; ++j) {
            Log.i("ZYStudio", "loop j:" + j);
        }
        Log.i("ZYStudio", "-----------------");
        int k = 0;
        //上边没有任何不同，但是下边这两个就有不同了
        while (k++ < 3) {
            Log.i("ZYStudio", "r43 loop k:" + k);
        }
        //这个相当于k+1之后，走循环，然后再比较,所以这个可以走到k为3
        //相当于 do k+1  while( <)
        Log.i("ZYStudio", "-----------------");
        k = 0;
        while (++k < 3) {
            Log.i("ZYStudio", "r48 loop k:" + k);
        }
        //这个相当于k+1之后，比较，比较成功之后才走循环，所以只能走到k为2，少走了一个循环
        //相当于 k+1 while(<) 
    }
}
