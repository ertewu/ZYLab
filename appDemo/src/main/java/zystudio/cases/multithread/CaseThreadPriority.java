package zystudio.cases.multithread;

import zystudio.mylib.utils.LogUtil;
import android.app.Activity;
import android.os.Process;
import android.util.Log;

/**
 * 测试 android中设置 线程优先级的
 * 
 */
public class CaseThreadPriority {

    private static CaseThreadPriority sCase;
    private static Activity mAct;

    public static CaseThreadPriority getInstance(Activity act) {
        if (sCase == null) {
            sCase = new CaseThreadPriority();
            sCase.init(act);
        }
        return sCase;
    }

    private void init(Activity act) {
        this.mAct = act;
    }

    public void work() {
        testBackGroundThread();
    }

    /**
     * 查看程序的不同情况下，线程是否还在运行 事实是即使finish了最后的activity，线程也仍然在运行。只有把进程停掉，线程才会停的
     */
    public void testThreadLife() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Log.i("ertewu", "Thread is living:" + System.currentTimeMillis());
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * 这个log中，两个thread执行交叉混乱，我并没有明确看出来什么状况
     * http://blog.sina.com.cn/s/blog_601cbd070100njbi.html 都是这一个demo而已
     */
    public void testThreadPriority() {
        MyThread a = new MyThread("Thread A");
        a.setOSPriority(Process.THREAD_PRIORITY_LOWEST); // 19
        a.setPriority(Thread.MAX_PRIORITY); // 10

        MyThread b = new MyThread("Thread B");
        b.setOSPriority(Process.THREAD_PRIORITY_URGENT_AUDIO); // -19
        b.setPriority(Thread.MIN_PRIORITY); // 1

        a.start();
        b.start();
    }

    public void testBackGroundThread() {

        final CountTask mainThreadTask = new CountTask();
        final CountTask bgThreadTask = new CountTask();
        Thread ThreadA = new Thread("ThreadA") {

            @Override
            public void run() {
                mainThreadTask.run();
            }
        };
        // ThreadA.setPriority(Process.THREAD_PRIORITY_DEFAULT);
        Thread ThreadC = new Thread("ThreadC") {
            @Override
            public void run() {
                bgThreadTask.run();
            }
        };
        ThreadC.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
        ThreadA.start();
        ThreadC.start();
    }

    private static class CountTask implements Runnable {

        static boolean sIsFinished;
        static int sCount = 0;
        final int TOTAL = 100000;
        static Object sLock = new Object();

        @Override
        public void run() {
            while (sCount < TOTAL) {
                    if (sIsFinished) {
                        break;
                    }
                    if (sCount % 10000 == 0) {
                        LogUtil.log("CountTask " + Thread.currentThread().getName() + " |sCount is:" + sCount);
                    }
                    if (sCount == TOTAL - 1) {
                        LogUtil.log("CountTask" + Thread.currentThread().getName() + " End");
                    }
                    sCount++;
                }
                sIsFinished = true;
        }
    }

    private static class MyThread extends Thread {
        private int mOSPriority = Process.THREAD_PRIORITY_DEFAULT;
        private int mLoopCount = 0;

        public MyThread(String threadName) {
            super(threadName);
        }

        public void setOSPriority(int p) {
            mOSPriority = p;
        }

        @Override
        public void run() {
            Process.setThreadPriority(mOSPriority);

            while (mLoopCount < 1000) {
                mLoopCount++;
                Math.log(Math.random() * 1000); // calculation test

                LogUtil.log(new StringBuilder().append(getName()).append(" os priority: ").append(mOSPriority)
                        .append(" java priority: ").append(getPriority()).append(" loop count: ").append(mLoopCount)
                        .toString());
            }

            LogUtil.log(new StringBuilder().append(getName()).append(" exiting...").append(" os priority: ")
                    .append(mOSPriority).append(" java priority: ").append(getPriority()).append(" loop count: ")
                    .append(mLoopCount).toString());
        }
    }
}
