package zystudio.cases.javabase;

import zystudio.mylib.utils.LogUtil;
import android.app.Activity;

/**
 * 用于演示子类,以及接口是否属于instanceof 判断正确的范围
 *
 * @author wzy
 *
 */
public class CaseInstanceof {

    private static CaseInstanceof sInstance;

    public static CaseInstanceof obtain(Activity activity) {

        if (null == sInstance) {
            sInstance = new CaseInstanceof();
        }
        return sInstance;
    }

    private CaseInstanceof() {

    }

    public void work() {
        Item myItem = new Item();
        ExtendItem myExtendItem = new ExtendItem();
        GrandChildItem myGrandChildItem=new GrandChildItem();

        boolean isRight = myItem instanceof Action;
        LogUtil.log("class instanceof interface result:" + isRight);
        isRight = myExtendItem instanceof Item;
        LogUtil.log("childclass instanceof baseClass result:" + isRight);
        isRight=myGrandChildItem instanceof Item;
        LogUtil.log("grandChild class instanceof baseClass result:" + isRight);
        //All answsers are true.
    }

    public static interface Action {
        public void doAction();
    }

    public static class Item implements Action {
        @Override
        public void doAction() {

        }

        public Item() {

        }
    }

    public static class ExtendItem extends Item {
        public ExtendItem() {

        }
    }

    public static class  GrandChildItem extends ExtendItem {
        public GrandChildItem() {

        }
    }
}
