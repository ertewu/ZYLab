package zystudio.cases.multithread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by leeco on 2017/7/29.
 * 主要是为了看 compareAndSet (CAS) 是不是阻塞的动作
 */
public class CaseCAS {

    public void work(){
        AtomicInteger value=new AtomicInteger(5);
        value.compareAndSet();
    }
}
