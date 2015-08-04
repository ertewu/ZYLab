package zystudio.cases.architecture;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.util.Log;

public class CaseBase{

    public static Object getInstance() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        Log.i("ZYStudio", "clsName:"+element1.getClassName());
//        Log.i("ZYStudio", "clsName:"+element2.getClassName());
        //最终证明 static函数无法感知类的层次，只能感觉到CaseBase，不能感觉到Case2
        for(int i=0;i<stackTraceElements.length;i++){
            Log.i("ZYStudio", "r17:"+i+"|"+ stackTraceElements[i].getClassName());
        }
//        return invokeInstrcutor(clsName);
        return null;
    }

    public static Object invokeInstrcutor(String className) {
        try {
            Class cls = Class.forName(className);
            //这是一个没有任何参数的构造函数
            Constructor[] constructors=cls.getConstructors();
            Constructor myConstructor=constructors[0];
            Object myObj=myConstructor.newInstance(null);
            return myObj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
