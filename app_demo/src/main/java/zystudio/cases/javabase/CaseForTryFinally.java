package zystudio.cases.javabase;

import android.util.Log;

import zystudio.mylib.utils.LogUtil;

/**
 * https://www.cnblogs.com/lanxuezaipiao/p/3440471.html  这里边的例子非常NB
 */
public class CaseForTryFinally {
    private static CaseForTryFinally sCase;

    public static CaseForTryFinally obtain(){
        if(sCase==null){
            sCase=new CaseForTryFinally();
        }
        return sCase;
    }

    private CaseForTryFinally(){

    }

    public void work(){
        boolean result=work1();
        //看这个是比finally 早还是晚
        LogUtil.log("result occured:"+result);

        workAsync1();
    }

    /**
     * 为了测试finally与return 谁先走的问题，打log和断点表示：finally在try中的return 之前，但在任何try中return之前的代码之后
     */
    private boolean work1(){
        try{
            Log.i("ZYStudio", "work1 try occured:"+Thread.currentThread().getName());
            return true;
        }finally{
            Log.i("ZYStudio", "work1 final occured:"+Thread.currentThread().getName());
        }
    }

    private void workAsync1(){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
               work1();
            }
        });
        t.start();
    }
}
