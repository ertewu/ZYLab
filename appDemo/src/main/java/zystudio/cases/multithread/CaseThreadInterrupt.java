package zystudio.cases.multithread;

import android.os.Handler;
import android.util.Log;

import zystudio.mylib.utils.LogUtil;
import zystudio.utils.MainHandler;

/**
 * Created by wangzhengyu on 2017/11/20.
 */

public class CaseThreadInterrupt {

    private static CaseThreadInterrupt sCase;
    public static CaseThreadInterrupt getInstance(){
        if(sCase==null){
            sCase=new CaseThreadInterrupt();
        }
        return sCase;
    }

    private CaseThreadInterrupt(){

    }

    public  void showCase(){
        final Thread mThread=new ShowInterruptThread();
        mThread.start();
        MainHandler.obtain().postDelayed(new Runnable() {
            @Override
            public void run() {
                mThread.interrupt();
                LogUtil.log("request interrupt occured");
            }
        },5000);
        MainHandler.obtain().postDelayed(new Runnable() {
            @Override
            public void run() {
                //事实证明，start是可以的
                mThread.start();
                LogUtil.log("request restart occured");
            }
        },8000);
    }

    private class ShowInterruptThread extends Thread{

        @Override
        public void run() {
            while(!isInterrupted() && true){
                for (int i=0; i< Integer.MAX_VALUE/100 ;i++){
                     if(i%100000==0){
                         LogUtil.log("looping:"+i);
                     }
                }
            }
            LogUtil.log("Interrutped occured:"+isInterrupted());
        }
    }
}
