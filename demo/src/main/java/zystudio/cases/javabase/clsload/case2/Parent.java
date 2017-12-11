package zystudio.cases.javabase.clsload.case2;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/3.
 */

public class Parent {

    public static String p_StaticField="Parent static var";

    public static String p_Field="Parent var";

    static {
        LogUtil.log(p_StaticField);
        LogUtil.log("Parent static block");
    }

    {
        LogUtil.log(p_Field);
        LogUtil.log("Parent block");
    }

    /**
     * Parent Class 的Constructor ，都走在 SubClass 的var 和initBlock 前边
     */
    public Parent(){
       LogUtil.log("Parent constructor");
    }
}
