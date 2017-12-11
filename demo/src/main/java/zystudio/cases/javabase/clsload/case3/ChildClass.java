package zystudio.cases.javabase.clsload.case3;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/12/3.
 */

public class ChildClass extends BaseClass{

    private int num=999;

    public ChildClass() {
        this.print();
    }

    @Override
    public void print() {
        LogUtil.log(this.getClass().toString()+",child's num="+num);
    }

}
