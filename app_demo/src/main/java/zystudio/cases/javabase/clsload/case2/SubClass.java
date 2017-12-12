package zystudio.cases.javabase.clsload.case2;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/3.
 */

public class SubClass extends Parent{

    public static String s_StaticField="SubClass static var";

    public String s_Field="SubClass var";

    static {
        LogUtil.log(s_StaticField);
        LogUtil.log("SubClass static block");
    }

    {
       LogUtil.log(s_Field);
       LogUtil.log("SubClass init blcok");
    }

    public SubClass(){
//        super();
       LogUtil.log("SubClass constructor");
    }

}
