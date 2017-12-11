package zystudio.cases.multithread;

import java.util.concurrent.locks.ReentrantLock;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by leeco on 2017/7/15.
 */

public class CaseReentrantLock {

    public void work() {
        Service service = new Service();
        Thread1 tA = new Thread1(service, "Thread-A");
        Thread1 tB = new Thread1(service, "Thread-B");
        Thread1 tC = new Thread1(service, "Thread-C");

        tA.start();
        tB.start();
        tC.start();


    }


    private static class Service {

        private ReentrantLock lock = new ReentrantLock();

        private void testMethod() {
            lock.lock();
            try {
                for (int i = 0; i < 3; i++) {
                    LogUtil.log(Thread.currentThread().getName() + " is printing " + i);

                    int holdCount = lock.getHoldCount();

                    int queuedLength = lock.getQueueLength();

                    boolean isFair = lock.isFair();

                    LogUtil.log("--holdCount:" + holdCount + "|queueLength:" + queuedLength + "|isFair:" + isFair);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }


    private static class Thread1 extends Thread {

        private Service service;

        public Thread1(Service service, String name) {
            super(name);
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.testMethod();
        }
    }

}
