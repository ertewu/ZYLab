package zystudio.cases.javabase.dynamicproxy.case1;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by wzy on 8/5/17.
 */

public class WangBaoQiang implements Actor {

    private String name;

    public WangBaoQiang() {
        this.name = "WangBaoQiang";
    }

    @Override
    public String sing(String name) {
        LogUtil.log(this.getName() + "sing:" + name);
        return "sing end";
    }

    @Override
    public String dance(String name) {
        LogUtil.log(this.getName() + "dance:" + name);
        return "dance end";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
