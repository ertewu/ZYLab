package zystudio.cases.multithread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import zystudio.demo.CaseEvenChecker;
import zystudio.mylib.utils.LogUtil;


// thinking in java 23.3.1的例子
public class CaseReentrantLock2 {


    public void work() {
        //多个线程用一个generator
        EvenChecker.test(new MutextEvenGenerator(), 10);
    }

    private static abstract class IntGenerator {

        private volatile boolean canceled = false;

        public abstract int next();

        public void cancel() {
            canceled = true;
        }

        public boolean isCanceled() {
            return canceled;
        }
    }

    public static class EvenChecker implements Runnable {
        private IntGenerator generator;
        private final int id;

        public EvenChecker(IntGenerator g, int ident) {
            generator = g;
            id = ident;
        }

        @Override
        public void run() {
            while (!generator.isCanceled()) {
                int val = generator.next();
                //
                if (val % 2 != 0) {
                    LogUtil.log(val + " not event!");
                    generator.cancel();
                }
            }
        }

        public static void test(IntGenerator gp, int count) {
            ExecutorService exec = Executors.newCachedThreadPool();
            for (int i = 0; i < count; i++) {
                exec.execute(new EvenChecker(gp, i));
            }
            exec.shutdown();
        }
    }

    public static class MutextEvenGenerator extends IntGenerator {

        private int currentEvenValue = 0;
        private Lock lock = new ReentrantLock();

        public int next() {
            lock.lock();
            try {
                LogUtil.log("Before yield Thread:" + Thread.currentThread().getName() + "|currentEvenValue is:" + currentEvenValue);
                ++currentEvenValue;
                Thread.yield();
                ++currentEvenValue;
                LogUtil.log("After  yield Thread:" + Thread.currentThread().getName() + "|currentEvenValue is:" + currentEvenValue);
                return currentEvenValue;
            } finally {
                lock.unlock();
            }
        }
    }
}
