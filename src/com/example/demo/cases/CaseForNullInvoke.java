package com.example.demo.cases;

import android.util.Log;


public class CaseForNullInvoke {

    private static CaseForNullInvoke sCase;


    public static CaseForNullInvoke obtain() {
        if (sCase == null) {
            sCase = new CaseForNullInvoke();
        }
        return sCase;
    }

    public void work(){
        DemoClass b=null;
        DemoClass a=b;
        DemoClass c=new DemoClass();
       b=c;

       //春哥说java是値引用，于是是null
       Log.i("ertewu", "a is null?:"+a);
    }

    public class DemoClass{

    }

}
