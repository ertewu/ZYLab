package zystudio.cases.javabase.clsload;

import android.app.Activity;

import zystudio.cases.javabase.clsload.case1.MyChildCls;
import zystudio.cases.javabase.clsload.case1.MyParentCls;
import zystudio.cases.javabase.clsload.case2.SubClass;
import zystudio.cases.javabase.clsload.case3.BaseClass;
import zystudio.cases.javabase.clsload.case3.ChildClass;
import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/3.
 */
public class CaseClsLoadOrder {

    public static void showCase(Activity activity){
//        showCase1();
//       showCase2();
       showCase3();
    }

    public static void showCase1(){
       MyChildCls child=new MyChildCls();
       child.test();

       MyParentCls parentItem=new MyChildCls();
       parentItem.test();
    }

    public static void showCase2(){
       new SubClass();
        LogUtil.log("-------------------showCase2 occured-------------------");
       new SubClass();
    }

    /**
     * http://www.th7.cn/Program/java/201603/772797.shtml  输出的第一个数是0额
     */
    public static void showCase3(){
        BaseClass child=new ChildClass();
    }

}
