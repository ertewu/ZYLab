package zystudio.cases.javabase.container;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import zystudio.mylib.utils.LogUtil;

/**
 * 这个是用来学习Java中的container的case，所谓container，我的理解至少是collection与Map
 * 这里跟着JAVA编程思想的container in deep 那章来走<br>
 * 这里有一个参考：http://txy821.iteye.com/blog/760357
 */
public class CaseArrayListNew {

    public CaseArrayListNew(){

    }

    public void work() {
        Bean bean1=new Bean(1,"sogou");
        Bean bean2=new Bean(2,"google");
        Bean bean3=new Bean(3,"baidu");

        ArrayList<Bean> list1=new ArrayList<Bean>();
        list1.add(bean1);
        list1.add(bean2);
        list1.add(bean3);


        /*从这个构造函数的实现来看, 也即ArrayList(Collection<? extends E> collection )
        * 里边确实new了一个新的数组
        * 就像自动扩容一样,这里的每个item搬运,也是用的System.arraycopy函数
        */
        ArrayList<Bean> list2=new ArrayList(list1);
        Iterator<Bean> iter1=list1.iterator();
        Iterator<Bean> iter2=list2.iterator();
        //万万没想到,他们的hashcode是一样的..真得看看ArrayList里的hashcode是怎么实现的..
        //看过了,这个hashcode是每一个item的hashcode算出来的..跟本对象无关
        LogUtil.log("list1 hashcode:"+list1.hashCode());
        LogUtil.log("list2 hashcode:"+list2.hashCode());
        //但是这两个并不一样..
        if(list1==list2){
           LogUtil.log("list 1   list 2  are identify");
        }else{
            LogUtil.log("list 1  is not list 2! ");
        }
        //子item的值也一样
        while(iter1.hasNext()){
            LogUtil.log(iter1.next().mName);
        }
        LogUtil.log("------------------");
        while(iter2.hasNext()){
            LogUtil.log(iter2.next().mName);
        }
        LogUtil.log("------------------");
        for(int i=0;i<3;i++){
            Bean item1=list1.get(i);
            Bean item2=list2.get(i);
            if(item1==item2){
                LogUtil.log(item1.mName+":two items are identify");
            }else{
                LogUtil.log(item1.mName+":two items are not identify");
            }
        }
    }

    private static class Bean {
        private int mIndex;
        private String mName;

        public Bean(int index,String name){
            mIndex=index;
            mName=name;
        }
    }

}
