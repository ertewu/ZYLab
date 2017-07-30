package zystudio.cases.multithread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import zystudio.mylib.utils.LogUtil;

public class CaseCallableAndFuture {
    private static CaseCallableAndFuture sInstance;

    public static CaseCallableAndFuture instance() {
        if (sInstance == null) {
            sInstance = new CaseCallableAndFuture();
        }
        return sInstance;
    }

    public void work() {

        ExecutorService exec = Executors.newCachedThreadPool();

        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
            LogUtil.log("add task " + i + "|at time:" + System.currentTimeMillis() % 100000);
        }
        // 事实证明,虽然这几个东西是在异线程的,但是这用子调用Future.get会阻塞主线程,这一点thinking in java 1125里说得很清楚
        // 主线程在这里就要等待这几个子线程干完活才走下边了
        for (Future<String> fs : results) {
            try {
                LogUtil.log("r76:" + fs.get() + "|currentTime:" + System.currentTimeMillis()
                        % 100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        LogUtil.log("faster than me ? haha");
    }

    class TaskWithResult implements Callable<String> {
        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }
        // Callable can return value ,which is maybe better than Runnable .
        @Override
        public String call() throws Exception {
            int sleep = 300;
            switch (id) {
            case 0:
                sleep = 300;
                break;
            case 1:
                sleep = 150;
                break;
            case 2:
                sleep = 250;
                break;
            case 3:
                sleep = 400;
                break;
            case 4:
                sleep = 200;
                break;

            default:
                break;
            }
            Thread.currentThread().sleep(sleep);
            return "result of TaskWith Result:" + id + "|finish time:" + System.currentTimeMillis()%100000;
        }
    }
}
