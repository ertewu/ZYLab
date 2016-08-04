package zystudio.cases.javabase;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import zystudio.mylib.utils.LogUtil;

/**
 * Referred From:
 * http://www.importnew.com/11038.html
 * http://zhidao.baidu.com/link?url=2WEU1ZffG_o-ja1ntpFNth0Q45sP4HYPKa4nEGCxRqftvPb3FJFSPFZIk1HdsMRIISKY61ryS37McVE8rNGkvK
 */
public class CaseForEachIterator {

    Collection<String> mList;

    public CaseForEachIterator(){ }

    private void initData(){
        mList=new ArrayList<String>();
        mList.add("Android");
        mList.add("Iphone");
        mList.add("Window Mobile");
    }

    public void work() {
        showErrCase1();
        showErrCase2();
        showCorrectCase();
    }

    private void showErrCase1(){
        LogUtil.log("-----------Err Case 1-------------");
        initData();
        try{
            Iterator<String> itr=mList.iterator();
            while (itr.hasNext()){
                String lang=itr.next();
                if(TextUtils.equals(lang,"Iphone")){
                    mList.remove(lang);
                }
            }
        }catch (Exception e){
            if(e!=null){
                LogUtil.log(e.getMessage());
                e.printStackTrace();
                /**
                 *   05-28 12:37:59.550 5636-5636/zystudio.demo W/System.err: java.util.ConcurrentModificationException
                 05-28 12:37:59.553 5636-5636/zystudio.demo W/System.err:     at java.util.ArrayList$ArrayListIterator.next(ArrayList.java:573)
                 */
            }
        }
    }

    private void showErrCase2(){
        LogUtil.log("-----------Err Case 2-------------");
        initData();
        try{
            for(String lang:mList){
                if(TextUtils.equals(lang,"Iphone")){
                    mList.remove(lang);
                }
            }
        }catch (Exception e){
            if(e!=null){
                LogUtil.log(e.getMessage());
                e.printStackTrace();
                /**
                 *   05-28 12:37:59.550 5636-5636/zystudio.demo W/System.err: java.util.ConcurrentModificationException
                 05-28 12:37:59.553 5636-5636/zystudio.demo W/System.err:     at java.util.ArrayList$ArrayListIterator.next(ArrayList.java:573)
                 */
            }
        }
    }

    private void showCorrectCase(){
        LogUtil.log("-----------Correct Case -------------");
        initData();
        try{
            Iterator<String> itr=mList.iterator();
            while (itr.hasNext()){
                String lang=itr.next();
                if(TextUtils.equals(lang,"Iphone")){
                    //这里要用迭代器的remove
                    itr.remove();
                }
            }

            printList();
        }catch (Exception e){
            if(e!=null){
                LogUtil.log(e.getMessage());
            }
        }
    }

    private void printList(){
        for(String lang:mList){
            LogUtil.log("item is:"+lang);
        }
    }

}
