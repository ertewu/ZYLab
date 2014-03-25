package com.example.demo.cases;

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
        testThreadLife();
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
                        Log.i("wzy",
                                "Thread is living:"
                                        + System.currentTimeMillis());
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

                Log.i("ertewu",
                        new StringBuilder().append(getName())
                                .append(" os priority: ").append(mOSPriority)
                                .append(" java priority: ")
                                .append(getPriority()).append(" loop count: ")
                                .append(mLoopCount).toString());
            }

            Log.i("ertewu",
                    new StringBuilder().append(getName()).append(" exiting...")
                            .append(" os priority: ").append(mOSPriority)
                            .append(" java priority: ").append(getPriority())
                            .append(" loop count: ").append(mLoopCount)
                            .toString());
        }
    }
}
