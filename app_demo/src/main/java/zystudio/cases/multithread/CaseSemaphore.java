package zystudio.cases.multithread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import zystudio.mylib.utils.LogUtil;

/**
 * 信号量的使用， 与 Object的wait和notify有啥不一样的好处
 *  http://fpcfjf.lofter.com/post/1cc5a33d_7d02d1a
 *  但是这个demo并不好，还要找新点的demo 试一试
 *
 *  这个还不错
 *  http://www.cnblogs.com/whgw/archive/2011/09/29/2195555.html
 */
public class CaseSemaphore {

    private static CaseSemaphore sCase;
    private ExecutorService mService;
    private final Semaphore semap=new Semaphore(1);

    private static char currentThread='A';

    public static CaseSemaphore getInstance() {
        if (sCase == null) {
            sCase = new CaseSemaphore();
        }
        return sCase;
    }

    private CaseSemaphore() {
        mService=Executors.newCachedThreadPool();
    }


    public void work() {
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        LogUtil.log("Accessing:"+NO);
                        Thread.sleep((long) (Math.random() * 10000));
                        // 访问完后，释放
                        semp.release();
                        LogUtil.log(":release "+NO+",availablePermits:"+semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            mService.execute(run);
        }
        // 退出线程池
        mService.shutdown();
    }

    /**
     * 这是那个不好用的demo
     */
    public void work1(){
        mService.execute(new RunnableA());
        mService.execute(new RunnableB());
    }

private class RunnableA implements Runnable {

    @Override
    public void run() {
        for(int i=1;i<=52;i++){
            try{
                semap.acquire();
                while(currentThread!='A'){
                    semap.release();
                }
                LogUtil.log(""+i);
                if(i%2==0){
                    currentThread='B';
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                semap.release();
            }
        }
    }
}

private class RunnableB implements Runnable {

    @Override
    public void run() {
        for(char c='A';c<'Z';c++) {
            try{
                semap.acquire();
                while(currentThread!='B'){
                    semap.release();
                }
                LogUtil.log(""+c);
                currentThread='A';
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                semap.release();
            }
        }
    }
}
}
