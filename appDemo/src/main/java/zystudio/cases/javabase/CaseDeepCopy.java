package zystudio.cases.javabase;

import zystudio.mylib.utils.LogUtil;

/**
 * Shallow Copy, Deep Copy 的Case,以及clone函数的理解 <{@link # CaseShallowCopy.java}
 * 第一种deepCopy的方法
 * http://www.jusfortechies.com/java/core-java/deepcopy_and_shallowcopy.php
 */
public class CaseDeepCopy {

    private static CaseDeepCopy sCase;

    public static CaseDeepCopy obtain() {
        if (sCase == null) {
            sCase = new CaseDeepCopy();
        }
        return sCase;
    }

    public void work() {
        showDeepCopy();
    }

    // public void showShallowCopy(){
    //
    // }

    public void showDeepCopy() {
        Student stud = new Student("John", "Algebra");
        LogUtil.log("Original Object:" + stud.getName() + "-" + stud.getSubj().getName());
        Student clonedStud = (Student) stud.clone();
        LogUtil.log("Cloned Object:" + clonedStud.getName() + "-" + clonedStud.getSubj().getName());

        stud.setName("Dan");
        stud.getSubj().setName("Physics");
        LogUtil.log("Original Object after it is updated:" + stud.getName() + "-" + stud.getSubj().getName());
        LogUtil.log("Cloned Object after it is updated:" + clonedStud.getName() + "-" + clonedStud.getSubj().getName());
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

    private static class Student implements Cloneable {
        private final Subject mSubj;
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

//        @Override
//        protected Object clone() {
//            /**
//             *这里我们发现，作为原类型的String name，两个对象的值已经不同了，但是作为自定义对象类型 的subject，两个一块改了。这就是浅复制 
//             */
//            try {
//                return super.clone();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }

         @Override
         public Object clone() {
         // Deep copy
         // 调用了一下构造函数，就是DeepCopy了,我们发现这两个值都完全不一样，subject的值也不一样了，说明生成了新的自定义对象类型
         Student s = new Student(mName, mSubj.getName());
         return s;
         }
    }
}
