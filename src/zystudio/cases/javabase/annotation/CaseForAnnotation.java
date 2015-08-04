package zystudio.cases.javabase.annotation;

import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;

/**
 * Demo from:
 * http://www.cnblogs.com/mandroid/archive/2011/07/18/2109829.html
 */
public class CaseForAnnotation {
    private static CaseForAnnotation sCase;
    private Activity mAct;

    public static CaseForAnnotation getInstance(Activity act) {
        if (sCase == null) {
            sCase = new CaseForAnnotation();
        }
        sCase.init(act);
        return sCase;
    }

    private void init(Activity act) {
        mAct = act;
    }

    public void work() {
        workForDemo();
    }

    private void workForDemo() {
        try {
            // 通过运行时反设API获得annotation信息
            Class rt_class = Class.forName("zystudio.cases.javabase.annotation.Utility");
            Method[] methods = rt_class.getMethods();
            boolean flag = rt_class.isAnnotationPresent(Description.class);

            if (flag) {
                Description description = (Description) rt_class.getAnnotation(Description.class);
                Log.i("ZYStudio", ("Utility's Description--->" + description.value()));
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Author.class)) {
                        Author author = method.getAnnotation(Author.class);
                        Log.i("ZYStudio", ("Utility's Author -->:"+ method.getName()+"|"+ author.name() + " from " + author.group()));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
