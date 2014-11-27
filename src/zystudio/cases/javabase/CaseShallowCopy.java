package zystudio.cases.javabase;

import zystudio.mylib.utils.LogUtil;

/**
 * Shallow Copy, Deep Copy 的Case,以及clone函数的理解
 * <{@link # CaseDeepCopy.java}
 * http://www.jusfortechies.com/java/core-java/deepcopy_and_shallowcopy.php
 */
public class CaseShallowCopy {

    private static CaseShallowCopy sCase;

    public static CaseShallowCopy obtain() {
        if (sCase == null) {
            sCase = new CaseShallowCopy();
        }
        return sCase;
    }

    public void work() {
        Student stud=new Student("John","Algebra");
        LogUtil.log("Original Object:"+stud.getName()+"-"+stud.getSubj().getName());
        Student clonedStud=(Student) stud.clone();
        LogUtil.log("Cloned Object:"+clonedStud.getName()+"-"+clonedStud.getSubj().getName());

        stud.setName("Dan");
        stud.getSubj().setName("Physics");
        LogUtil.log("Original Object after it is updated:"+stud.getName()+"-"+stud.getSubj().getName());
        LogUtil.log("Cloned Object after it is updated:"+clonedStud.getName()+"-"+clonedStud.getSubj().getName());
        /*
         *
         */
    }

    private static class Subject {
        private String mName;

        public Subject(String s) {
            mName = s;
        }

        public String getName() {
            return mName;
        }

        public void setName(String s) {
            mName = s;
        }
    }

    private static class Student  implements Cloneable{
        private Subject mSubj;
        private String mName;

        public Student(String s, String sub) {
            mName = s;
            mSubj = new Subject(sub);
        }

        public Subject getSubj() {
            return mSubj;
        }

        public String getName() {
            return mName;
        }

        public void setName(String s) {
            mName = s;
        }

        @Override
        public Object clone()  {
            try {
                //用的super.clone实际上是调用了Object.clone方法，
                //Object有clone方法但是从没有调用过就像一个utility方法，而且是protected
                //而你这个Object继承了Cloneable接口，然后在接口中调用Object.clone方法，这函数就成public的了
                //通过这个实验也得出调用 Object.clone，实际上是浅复制，元类型name没有再多生成一个
                return super.clone();
            }catch (CloneNotSupportedException e){
              return null;
            }
        }
    }
}
