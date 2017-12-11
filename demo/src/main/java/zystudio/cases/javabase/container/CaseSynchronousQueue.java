package zystudio.cases.javabase.container;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/10.
 * http://blog.csdn.net/hudashi/article/details/7076814
 */

public class CaseSynchronousQueue {

    static int cnt=0;
    private static CaseSynchronousQueue sCase;

    public static CaseSynchronousQueue obtain(){
       if(sCase==null){
           sCase=new CaseSynchronousQueue();
       }
       return sCase;
    }

    public void showCase(){
        SynchronousQueue<String > queue=new SynchronousQueue<>();
        for(int i=0;i<10;i++){
            new Thread(new ThreadProducter(queue)).start();
        }
        for(int i=0;i<10;i++){
            new Thread(new ThreadConsumer(queue)).start();
        }
    }

    class ThreadProducter implements Runnable {

        SynchronousQueue<String> queue;
        int cnt=0;
        ThreadProducter(SynchronousQueue<String> queue){
            this.queue=queue;
        }

        @Override
        public void run() {
            String name="";
            int val=0;
            Random random=new Random(System.currentTimeMillis());
            while(true){
                cnt=(cnt+1)&0xFFFFFFFF;
                try {
                   val=random.nextInt()%15;
                   if(val<5){
                       name="offer name:"+cnt;
                       queue.offer(name);
                   }else if(val<10){
                      name="put name:"+cnt;
                      queue.put(name);
                   }else{
                       name="offer wait time and name:"+cnt;
                       queue.offer(name,1000, TimeUnit.MILLISECONDS);
                   }
                   Thread.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadConsumer implements Runnable{

        SynchronousQueue<String> queue;
        ThreadConsumer(SynchronousQueue<String> queue){
           this.queue=queue;
        }

        @Override
        public void run() {
            String name;
            while(true){
               try{
                   name=queue.take();
                   LogUtil.log("take:"+name);
                   Thread.sleep(1);
               }catch (InterruptedException e){
                  e.printStackTrace();
               }
            }
        }
    }

}
