package zystudio.cases.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import zystudio.mylib.utils.LogUtil;

/**
 * Future的Cancel函数，其实用法很恶心的，必须得自己实验一下了
 * 找一些网上的说法：
 * https://stackoverflow.com/questions/9536555/utility-of-future-cancelboolean-method
 * <p>
 * https://stackoverflow.com/questions/21445224/what-does-future-cancel-do-if-not-interrupting
 */
public class CaseFutureCancel {
    private static CaseFutureCancel sInstance;

    public static CaseFutureCancel instance() {
        if (sInstance == null) {
            sInstance = new CaseFutureCancel();
        }
        return sInstance;
    }

    public void work() {
//        showCancelTrueDemo();
        showCancelFalseDemo();
    }

    private Future<?> cancelTrueFuture;

    private void showCancelTrueDemo() {
        ExecutorService eService = Executors.newFixedThreadPool(5);
        cancelTrueFuture = eService.submit(new CancelTrueFuture());
        LogUtil.log("future submit occured");
        try {
            Thread.sleep(10);
            boolean result = cancelTrueFuture.cancel(true);
            LogUtil.log("future cancel occured：" + result);
        } catch (InterruptedException e) {
            LogUtil.log("InterruptedException occured:" + e.getMessage());
        }
    }

    private Future<?> cancelFalseFuture;

    //没有看到false有啥鸟用, 就如第二个stackoverflow上讲，标了一个cancel而已
    private void showCancelFalseDemo() {
        ExecutorService eService = Executors.newFixedThreadPool(5);
        cancelFalseFuture = eService.submit(new CancelFalseFuture());
        LogUtil.log("future submit occured");
        try {
            Thread.sleep(1000);
            cancelFalseFuture.cancel(false);
            LogUtil.log("future cancel occured");
        } catch (InterruptedException e) {
            LogUtil.log("InterruptedException occured:" + e.getMessage());
        }
    }

    class CancelTrueFuture implements Callable<String> {

        int i = 0;

        @Override
        public String call() throws Exception {
            while (i < 10000) {
                //如果把底下的isInterrupted 的处理都去掉，那么上边的cancel 函数，返回的结果，就是false，也就是没能cancel成功
                if (!Thread.currentThread().isInterrupted()) {
                    LogUtil.log("i++:" + i);
                    i++;
                } else {
                    LogUtil.log("callable Interrupted occured,i ==" + i);
                    break;
                }
            }
            return "~~";
        }
    }

    class CancelFalseFuture implements Callable<String> {

        int i = 0;

        @Override
        public String call() throws Exception {
            while (i < 1000) {
                Thread.sleep(50);
                if (!Thread.currentThread().isInterrupted()) {

                    LogUtil.log("i++:" + i);
                    //当Future.cancel 传为false时，就仅此而已，是这样的没别的了
                    LogUtil.log("cancelFalseFuture.isCanceled:" + cancelFalseFuture.isCancelled()
                    );
                    i++;
                } else {
                    LogUtil.log("callable Interrupted occured,i ==" + i);
                    break;
                }
            }
            return "~~";
        }
    }
}
