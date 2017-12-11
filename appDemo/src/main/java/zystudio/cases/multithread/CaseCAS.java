package zystudio.cases.multithread;

import android.os.Process;

import java.util.concurrent.atomic.AtomicInteger;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by leeco on 2017/7/29.
 * 主要是为了看 compareAndSet (CAS) 是不是阻塞的动作
 */
public class CaseCAS {

    AtomicInteger myInteger;

    public void work() {
        myInteger = new AtomicInteger(0);
        Thread1 myThread1 = new Thread1();
        Thread2 myThread2 = new Thread2();
        myThread2.start();
        myThread1.start();
    }


    //这个就是AtomicInteger的incrementAndGet的实现来试一试
    //事实证明，compareAndSet 也即cas是不阻塞线程的，但是确实是有成功和失败之说，所以要有一个无限循环一直去尝试修改。所以可以想像这玩意在多线程时，是耗资源的...因为自旋等待
    //但是我们发现，其实自旋锁，也没有那么块，有时能超过1ms，所以确实是非阻塞的，但没有想得那么快，特别快的的循环
    //不过也看出，确实在我的log之间，没有另一个线程进行中断。挺奇怪的
    //http://zl198751.iteye.com/blog/1848575  这篇文章里有讲了，cas非阻塞什么的
    public final int incrementAndGet() {
        for (; ; ) {
            int current = myInteger.get();
            int next = current + 1;
            LogUtil.log("Thread:" + Thread.currentThread().getName() + "| Before time:" + System.currentTimeMillis() % 10000);
            if (myInteger.compareAndSet(current, next)) {
                LogUtil.log("Thread:" + Thread.currentThread().getName() + "|Result true|After time:" + System.currentTimeMillis() % 10000);
                return next;
            } else {
                LogUtil.log("Thread:" + Thread.currentThread().getName() + "|Result false|After time:" + System.currentTimeMillis() % 10000);
            }
        }
    }

    private class Thread1 extends Thread {

        public Thread1() {
            setName("Thread1");
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                incrementAndGet();
            }
        }
    }

    private class Thread2 extends Thread {

        public Thread2() {
            setName("Thread2");
        }

        @Override
        public void run() {
            //这个线程优先级，并没有太大作用的样子
//            Process.setThreadPriority(Process.THREAD_PRIORITY_FOREGROUND);
            for (int i = 0; i < 100; i++) {
                incrementAndGet();
            }
        }
    }

}
