package zystudio.cases.multithread;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import zystudio.mylib.utils.LogUtil;

/**
 * http://blog.csdn.net/j754379117/article/details/47364359
 * 这个例子确实简单明了
 */
public class CaseCountDownLatch {

    private static CaseCountDownLatch sCase;

    public static CaseCountDownLatch obtain() {
        if (sCase == null) {
            sCase = new CaseCountDownLatch();
        }
        return sCase;
    }

    private CountDownLatch mlatch;

    public void work() {

        ExecutorService executor = Executors.newCachedThreadPool();

        mlatch = new CountDownLatch(3);

        Worker w1 = new Worker("张三");
        Worker w2 = new Worker("李四");
        Worker w3 = new Worker("王二");

        Boss boss = new Boss();

        executor.execute(boss);
        executor.execute(w3);
        executor.execute(w2);
        executor.execute(w1);

        executor.shutdown();
    }


    public class Boss implements Runnable {

        public Boss() {
        }

        @Override
        public void run() {
            LogUtil.log("老板正在等所有的工人干完活......");
            try {
                mlatch.await();
            } catch (InterruptedException e) {
            }
            LogUtil.log("工人活都干完了，老板开始检查了！");
        }

    }

    public class Worker implements Runnable {
        private String name;

        public Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            LogUtil.log(this.name + "正在干活...");
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            } catch (InterruptedException ie) {
            }
            LogUtil.log(this.name + "活干完了！");
            mlatch.countDown();

        }
    }
}
