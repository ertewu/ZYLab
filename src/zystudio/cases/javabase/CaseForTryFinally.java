package zystudio.cases.javabase;

import android.util.Log;

public class CaseForTryFinally {
    private static CaseForTryFinally sCase;

    public static CaseForTryFinally obtain(){
        if(sCase==null){
            sCase=new CaseForTryFinally();
        }
        return sCase;
    }

    private CaseForTryFinally(){

    }

    /**
     * 为了测试finally与return 谁先走的问题，打log和断点表示：finally在try中的return 之前，但在任何try中return之前的代码之后
     */
    public void work(){
        try{
            Log.i("ertewu", "r21 occured");
            return ;
        }finally{
            Log.i("ertewu", "finally occured");
        }
    }
}
