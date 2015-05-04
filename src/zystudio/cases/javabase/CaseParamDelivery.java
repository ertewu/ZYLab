package zystudio.cases.javabase;

import android.util.Log;
/**
 * http://blog.csdn.net/ithomer/article/details/6906797
 *
 */
public class CaseParamDelivery {
    

    public static void work() {
        String str1 = "abc";
        String str2 = "efg";

        swapString(str1, str2);
        Log.i("ZYStudio", "str1 and str2 is:" + str1 + "|" + str2);// 没有变
        String str3 = new String("abc");
        String str4 = new String("efg");
        swapString(str3, str4);
        Log.i("ZYStudio", "str3 and str4:" + str3 + "|" + str4);// 没有变
        StrWrap strW1 = new StrWrap("abc");
        StrWrap strW2 = new StrWrap("efg");
        swapStrWrap(strW1, strW2);
        Log.i("ZYStudio", "strW1 and strW2:" + strW1.getStr() + "|" + strW2.getStr());// 没有变
        swapStrWrap2(strW1, strW2);
        Log.i("ZYStudio", "strW1 and strW2:" + strW1.getStr() + "|" + strW2.getStr());//变了 
    }

    private static void swapString(String str1, String str2) {
        String tmp = str1;
        str1 = str2;
        str2 = tmp;
    }

    private static void swapStrWrap(StrWrap str1, StrWrap str2) {
        StrWrap tmp = str1;
        str1 = str2;
        str2 = tmp;
    }
    private static void swapStrWrap2(StrWrap str1, StrWrap str2) {
        String tmp = str1.mStr;
        str1.mStr=str2.mStr;
        str2.mStr= tmp;
    }

    private static class StrWrap {
        public String mStr;

        public StrWrap() {
            mStr = "";
        }

        public StrWrap(String str) {
            this.mStr = str;
        }

        public String getStr() {
            return mStr;
        }
    }
}
