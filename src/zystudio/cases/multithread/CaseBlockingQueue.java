package zystudio.cases.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import zystudio.mylib.utils.LogUtil;

/**
 * http://wsmajunfeng.iteye.com/blog/1629354
 * 用于实验java.nio.concurry中的ＢlockingQueue
 */
public class CaseBlockingQueue {

    private static CaseBlockingQueue sCase;
    public static CaseBlockingQueue getInstance(){
        if(sCase==null){
            sCase=new CaseBlockingQueue();
        }
        return sCase;
    }
    private CaseBlockingQueue(){

    }
    public void showCase(){
        try{
            BlockingQueue<String> queue=new LinkedBlockingDeque<String>(10);

            Producter pd1=new Producter(queue);
            Producter pd2=new Producter(queue);
            Producter pd3=new Producter(queue);
            Consumer consumer=new Consumer(queue);
            //thread setup
            ExecutorService service=Executors.newCachedThreadPool();
            service.execute(pd1);
            service.execute(pd2);
            service.execute(pd3);
            service.execute(consumer);
            //execute
            Thread.sleep(5*1000);
            pd1.stop();
            pd2.stop();
            pd3.stop();
            Thread.sleep(2000);
            service.shutdown();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static class Producter implements Runnable{
        private volatile boolean isRunning=true;
        private BlockingQueue queue;
        //atomicInteger:在java语言中，++i与i++并不是线程安全的，atomicInteger通过一种线程安全的加减操作接口
        private static AtomicInteger count=new AtomicInteger();
        private static final int DEFAULT_RANGE_FOR_SLEEP=1000;

        public Producter(BlockingQueue queue){
            this.queue=queue;
        }

        public void stop(){
            isRunning=false;
        }

        @Override
        public void run() {
            String data=null;
            Random r=new Random();
//            LogUtil.log("Setup producter thread:"+Thread.currentThread().getId()+"_"+Thread.currentThread().getName());
//            LogUtil.log("Setup producter thread");
            try{
                while(isRunning){
//                    LogUtil.log("producting data start!");
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                    data="data:"+count.incrementAndGet();
                    LogUtil.log("Puting data into list:"+data);
                    if(!queue.offer(data, 2, TimeUnit.SECONDS)){
                        LogUtil.log("Put data into list error:"+data);
                    }
                }
            }catch(InterruptedException e){
//                e.printStackTrace();
                LogUtil.log("producter execption:"+e.getMessage());
                Thread.currentThread().interrupt();
            }finally{
                LogUtil.log("Exit producter thread!");
            }
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
//                    LogUtil.log("Fetching Data is queue");
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
