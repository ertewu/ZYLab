package zystudio.demo.cases;

import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;
import android.view.ViewConfiguration;

public class CaseViewConfiguration {

    private  static CaseViewConfiguration sCase;
    private Activity mAct;

    public static CaseViewConfiguration obtain(Activity act){
        if(sCase==null){
            sCase=new CaseViewConfiguration(act);
        }
        return sCase;
    }

    private CaseViewConfiguration(Activity act){
        this.mAct=act;
    }

    public void work(){
        showConfigLog();
    }

    private void showConfigLog(){
        Class cls=ViewConfiguration.class;
        Method[] array=cls.getMethods();
        for(Method method:array){
            try {
                method.setAccessible(true);
                if(method.getName().startsWith("get")){
                    Log.i("ertewu", method.getName()+":"+method.invoke(ViewConfiguration.get(mAct),  (Object[]) null));
                }
            } catch (Exception e) {
                Log.i("ertewu", "showConfigLog error:"+e.toString());
            }
        }
    }
}
