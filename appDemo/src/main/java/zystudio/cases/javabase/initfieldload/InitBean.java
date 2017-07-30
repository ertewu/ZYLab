package zystudio.cases.javabase.initfieldload;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by leeco on 2017/7/17.
 */

class InitBean {

    public InitBean() {
        LogUtil.log("haha initBean occured");
    }

    public InitBean(String str) {
        LogUtil.log("heihei initBean:" + str + " occured");
    }
}
