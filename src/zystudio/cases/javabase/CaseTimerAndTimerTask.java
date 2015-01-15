package zystudio.cases.javabase;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import zystudio.mylib.utils.LogUtil;
/**
 *
 * Timer与TimerTask虽然刚会用,但是文档上已经推荐用ScheduledThreadPoolExecutor了
 *
 */
public class CaseTimerAndTimerTask {
    private static CaseTimerAndTimerTask sCase;

    public static CaseTimerAndTimerTask intance() {
        if (sCase == null) {
            sCase = new CaseTimerAndTimerTask();
        }
        return sCase;
    }

    private CaseTimerAndTimerTask() {

    }

    public void work() {
        try {
            TimerTask mTimerTask = new MyTimerTask();
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(mTimerTask, 0, 10 * 1000);
            LogUtil.log("TimerTask started:Thread:"+Thread.currentThread().getName()+"|"+new Date());
            Thread.sleep(120000);
            timer.cancel();
            LogUtil.log("TimerTask canceled");
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            try {
                LogUtil.log("TimerTask start at:" + new Date()+"|Thread:"+Thread.currentThread().getName()+","+Thread.currentThread().getId());
                //可以看到这个2w比上边的10*1000时间更长,于是上边的10*1000的rate就不行了,得按2w的这个rate走
                //当然如果去掉这个2w的话,就是按10000的频率固定拉起这个线程的
                Thread.sleep(20000);
                LogUtil.log("TimerTask end at:" + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
