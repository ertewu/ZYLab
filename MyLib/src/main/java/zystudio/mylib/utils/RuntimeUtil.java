package zystudio.mylib.utils;

import android.app.Instrumentation;


public class RuntimeUtil {


    public static  class ObjectSizeFetcher {
        private static Instrumentation instrumentation;

        public static void premain(String args, Instrumentation inst) {
            instrumentation = inst;
        }

//        public static long getObjectSize(Object o) {
//            return instrumentation.getObjectSize(o);
//        }
    }
}
