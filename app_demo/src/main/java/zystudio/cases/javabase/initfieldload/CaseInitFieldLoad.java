package zystudio.cases.javabase.initfieldload;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by leeco on 2017/7/17.
 */

class CaseInitFieldLoad extends CaseInitFieldLoadBase {

    private InitBean beanInChild = new InitBean("subcls");

    /**
     * 结果是：
     * I/ZYStudio: heihei initBean:base occured
     * I/ZYStudio: heihei initBean:subcls occured
     * I/ZYStudio: CaseInitFieldLoad Constructor occured
     * <p>
     * 即先加载基类，再加载子类，再加载 构造函数
     */
    public CaseInitFieldLoad() {
        LogUtil.log("CaseInitFieldLoad Constructor occured");
    }
}
