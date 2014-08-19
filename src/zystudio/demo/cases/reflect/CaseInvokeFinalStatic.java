package zystudio.demo.cases.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import zystudio.mylib.utils.LogUtil;
import android.text.TextUtils;

public class CaseInvokeFinalStatic {

    private static CaseInvokeFinalStatic sCase;

    public static CaseInvokeFinalStatic obtain() {
        if (sCase == null) {
            sCase = new CaseInvokeFinalStatic();
        }
        return sCase;
    }

    public void work(){
        logTriggers();
    }

    // NB:http://stackoverflow.com/questions/7560285/what-is-the-best-way-to-check-if-a-field-is-final-in-java-using-reflection
    private void logTriggers() {
        Class<?> myClass = FinalStaticData.class;
        Field[] fieldList = myClass.getDeclaredFields();
        for (Field item : fieldList) {
            int modifiers = item.getModifiers();
            if (Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers)) {
                item.setAccessible(true);
                if (item.getType() == String.class) {
                    try {
                        String content = (String) item.get(null);
                        if (!TextUtils.isEmpty(content)
                                && content.contains("TRIGGER ")) {
                            LogUtil.log(content);
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
