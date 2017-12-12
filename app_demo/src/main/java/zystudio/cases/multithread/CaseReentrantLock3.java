package zystudio.cases.multithread;

import android.util.Log;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import zystudio.mylib.utils.LogUtil;


/**
 * http://uule.iteye.com/blog/1488356
 */
public class CaseReentrantLock3 {

    public void work() {
        //多个线程用一个generator
//        test1();
        test2();
    }

    TestLock tester = new TestLock();

    public void test1() {
        //1、测试可重入
        tester.testReentry();
        LogUtil.log("this occured");
        tester.testReentry(); // 能执行到这里而不阻塞，表示锁可重入
        LogUtil.log("this 2 occured");
        tester.testReentry(); // 再次重入
        LogUtil.log("this 3 occured");

        // 释放重入测试的锁，要按重入的数量解锁，否则其他线程无法获取该锁。
        tester.getLock().unlock();
        tester.getLock().unlock();
        tester.getLock().unlock();

    }

    public void test2() {
        //2、测试互斥
        // 启动3个线程测试在锁保护下的共享数据data的访问
        new Thread(new workerThread(tester)).start();
        new Thread(new workerThread(tester)).start();
        new Thread(new workerThread(tester)).start();

    }

    private static class TestLock {
        private ReentrantLock lock = null;

        public int data = 100;     // 用于线程同步访问的共享数据

        public TestLock() {
            lock = new ReentrantLock(); // 创建一个自由竞争的可重入锁
        }

        public ReentrantLock getLock() {
            return lock;
        }

        public void testReentry() {
            lock.lock();
            Calendar now = Calendar.getInstance();
            LogUtil.log(now.getTime() + " " + Thread.currentThread() + " get lock.");
        }


        // 线程调用的方法
        public void testRun() throws Exception {
            lock.lock();

            Calendar now = Calendar.getInstance();
            try {
                // 获取锁后显示 当前时间 当前调用线程 共享数据的值（并使共享数据 + 1）
                LogUtil.log(now.getTime() + " " + Thread.currentThread() + " accesses the data " + data++);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    // 工作线程，调用TestServer.testRun
    private static class workerThread implements Runnable {

        private TestLock tester = null;

        public workerThread(TestLock testLock) {
            this.tester = testLock;
        }

        public void run() {
            try {
                tester.testRun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
