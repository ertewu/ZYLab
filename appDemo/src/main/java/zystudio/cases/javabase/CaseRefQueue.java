package zystudio.cases.javabase;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by wangzhengyu on 2017/10/23.
 * ReferenceQueue 用法
 * http://www.jianshu.com/p/73260a46291c
 */

public class CaseRefQueue {

    private static ReferenceQueue<byte[]> rq=new ReferenceQueue<byte[]>();

    private static int _1M=1024*1024;

    private static CaseRefQueue sInstance;
    public static CaseRefQueue obtain(){
        if(sInstance==null){
            sInstance=new CaseRefQueue();
        }
        return sInstance;
    }
    public static void showDemo(){
        Object value=new Object();
        Map<Object,Object> map=new HashMap<Object,Object>();
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
        thread.setDaemon(true);
        thread.start();

        for(int i=0; i< 10000; i++){
            byte[] bytes=new byte[_1M];
            WeakReference<byte[]> weakReference=new WeakReference<byte[]>(bytes,rq);
            map.put(weakReference,value);
        }
        LogUtil.log("map.size->"+map.size());
    }
}
