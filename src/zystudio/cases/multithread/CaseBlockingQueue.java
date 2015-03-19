package zystudio.cases.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import zystudio.mylib.utils.LogUtil;

/**
 * http://wsmajunfeng.iteye.com/blog/1629354
 * 用于实验java.nio.concurry中的ＢlockingQueue
 */
public class CaseBlockingQueue {

    public void showCase(){
        BlockingQueue<String> queue=new LinkedBlockingDeque<String>(10);
    }

    private static class Producter implements Runnable{
        @Override
        public void run() {
            // TODO Auto-generated method stub

        }

    }

    private static class Consumer implements Runnable{
        private static final int DEFAULT_RANGE_FOR_SLEEP=10;
        private BlockingQueue<String> mQueue;
        public Consumer(BlockingQueue<String> queue){
            this.mQueue=queue;
        }
        @Override
        public void run() {
            Random r=new Random();
            boolean isRunning=true;
            try{
                while(isRunning){
                    LogUtil.log("Fetching Data is queue");
                    String data=mQueue.poll(2, TimeUnit.SECONDS);
                    if(data!=null){
                        LogUtil.log("Got it! then is comsuming:"+data);
                        Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                    }else{
                        isRunning=false;
                    }
                }
            }catch(InterruptedException e){
                if(e!=null)
                LogUtil.log(e.getMessage());
            }finally{
                LogUtil.log("Exit Consumer thread");
            }
        }
    }
}
