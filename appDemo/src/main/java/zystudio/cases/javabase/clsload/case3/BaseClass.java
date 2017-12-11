package zystudio.cases.javabase.clsload.case3;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/3.
 */

public class BaseClass {

    private int num=1;

    public BaseClass(){
        print();
    }

    public void print(){
        //是0额，是0额，父类的构造函数调用时，子类的成员变量还没初始化呢，所以连1都不是，是0
        LogUtil.log(this.getClass().toString()+",base's num="+num);
    }
}
