package zystudio.cases.multithread;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by leeco on 2017/7/3.
 */
public class CaseNotifyVsNotifyAll {


    private static final Object obj = new Object();

    private static class MyRunnable implements Runnable {
        int i;

        MyRunnable(int i) {
            this.i = i;
        }

        public void run() {
            try {
                synchronized (obj) {
                    LogUtil.log("线程->" + i + " 等待中");
                    //wait 会把obj 锁交出去，但是sleep 可不会把锁交出去的
                    obj.wait();
                    LogUtil.log("线程->" + i + " 在运行了,马上sleep阶段");
                    Thread.sleep(10000);
                    LogUtil.log("线程->" + i + " Sleep完毕");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void work(){
        showDemo();
    }

    private static void showDemo() {

        Thread[] threads= new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new MyRunnable(i));
        }
        for (Thread each : threads) {
            each.start();
        }
        try {
            Thread.sleep(5000);
        } catch (Exception e) { }
        synchronized (obj) {
//            obj.notifyAll();
//            LogUtil.log("mainThread notifyAll occured");
            obj.notify();
            LogUtil.log("mainThread notify occured");
        }
    }
}
//
//NotifyAll 结果:
//        线程->  0 等待中
//线程->  3 等待中
//线程->  2 等待中
//线程->  1 等待中
//线程->  6 等待中
//线程->  5 等待中
//线程->  8 等待中
//线程->  4 等待中
//线程->  9 等待中
//线程->  7 等待中
//线程->  7 在运行了
//线程->  9 在运行了
//线程->  4 在运行了
//线程->  8 在运行了
//

//Notify的结果

//线程->  0 等待中
//        线程->  4 等待中
//        线程->  3 等待中
//        线程->  2 等待中
//        线程->  1 等待中
//        线程->  8 等待中
//        线程->  7 等待中
//        线程->  6 等待中
//        线程->  5 等待中
//        线程->  9 等待中
//        线程->  0 在运行了

// 这样子的,已经能看到结果了.但是这是可以看出,这跟sync块有很大的关系