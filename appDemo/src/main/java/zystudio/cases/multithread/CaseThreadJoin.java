package zystudio.cases.multithread;

import zystudio.mylib.utils.LogUtil;

/**
 * http://blog.csdn.net/bzwm/article/details/3881392
 *
 */
public class CaseThreadJoin {

    private static CaseThreadJoin sCase;

    public final static CaseThreadJoin obtain() {
        if (sCase == null) {
            sCase = new CaseThreadJoin();
        }
        return sCase;
    }

    public void work() {
//        showDemo1();
        // showDemo2();
        showSimpleDemo();
    }

    private void showDemo1() {
        String threadName = Thread.currentThread().getName();
        LogUtil.log(threadName + " start.");
        ChildThread childThread = new ChildThread();
        ParentThread customThread = new ParentThread(childThread);
        try {
            childThread.start();
            Thread.sleep(2000);
            customThread.start();
            customThread.join();
        } catch (Exception e) {
            LogUtil.log("Exception from main");
        }
        LogUtil.log(threadName + " end.");
    }

    private void showDemo2() {
        String threadName = Thread.currentThread().getName();
        LogUtil.log(threadName + " start.");
        ChildThread childThread = new ChildThread();
        ParentThread parentThread = new ParentThread(childThread);
        try {
            childThread.start();
            Thread.sleep(2000);
            parentThread.start();
        } catch (Exception e) {
            LogUtil.log("Exception from main");
        }
        LogUtil.log(threadName + " end.");
    }

    /**
     * 这是我自己写的demo，那个网址的例子还是稍微显得复杂点..,作对比的话把thread1.join()注掉，看log就可以
     */
    private void showSimpleDemo() {
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        LogUtil.log("thread1 loop index:" + i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        LogUtil.printTime("main thread start");
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtil.printTime("main thread end");
    }

    private static class ParentThread extends Thread {
        ChildThread mChildThread;

        public ParentThread(ChildThread t1) {
            super("[ParentThread] Thread");
            this.mChildThread = t1;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            LogUtil.log(threadName + " start. ");
            try {
                mChildThread.join();
                LogUtil.log(threadName + " end. ");
            } catch (Exception e) {
                LogUtil.log("Exception from " + threadName + ".run");
            }
        }
    }

    private static class ChildThread extends Thread {

        public ChildThread() {
            super("[ChildThread] Thread");
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            LogUtil.log(threadName + " start.");
            try {
                for (int i = 0; i < 10; i++) {
                    LogUtil.log(threadName + " loop at " + i);
                    Thread.sleep(1000);
                }
                LogUtil.log(threadName + " end.");
            } catch (Exception e) {
                LogUtil.log("Exception from " + threadName + ".run");
            }
        }

    }
}
