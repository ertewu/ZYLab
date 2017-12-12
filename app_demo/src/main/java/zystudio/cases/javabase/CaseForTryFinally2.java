package zystudio.cases.javabase;


import zystudio.mylib.utils.LogUtil;

public class CaseForTryFinally2 {
    private static CaseForTryFinally2 sCase;

    public static CaseForTryFinally2 obtain(){
        if(sCase==null){
            sCase=new CaseForTryFinally2();
        }
        return sCase;
    }

    private CaseForTryFinally2(){

    }

    public void work(){
        try{
            testEx();
        }catch (Exception e){

        }
    }

    private boolean testEx() throws Exception{
        boolean ret=true;
        try{
           ret=testEx1();
        }catch (Exception e){
            System.out.println("testEx,catch exception");
            ret=false;
            throw e;
        }finally {
            System.out.println("testEx,finally; return value="+ret);
            return ret;
        }
    }

    boolean testEx1() throws  Exception {
        boolean ret=true;
        try{
            ret=testEx2();
            if(!ret){
                return false;
            }
            LogUtil.log("TestEx1 ,at the end of try");
            return ret;
        }catch (Exception e){
            System.out.println("TestEx1, catch Exception");
            ret=false;
            throw e;
        }finally {
            System.out.println("TextEx1,finally; return value="+ret);
            return ret;
        }
    }

    boolean testEx2() throws Exception {
       boolean ret=true;
       try{
           int b=12;
           int c;
           for(int i=2;i>=-2;i--){
               c=b/i;
               LogUtil.log("i="+i);
           }
           return true;
       }catch (Exception e){
           LogUtil.log("TestEx2 catch Exception");
           ret=false;
           throw e;
       }finally {
           LogUtil.log("testEx2 finally; return value="+ret);
           return ret;
       }
    }
}
