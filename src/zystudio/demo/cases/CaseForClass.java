package zystudio.demo.cases;

/**
 * 用来实验java的class类的一些属性，class类是个很奇特的类，在抽象与泛型，与反射这类用法中有很重要的地方。好像也挺好玩的
 * 我举一些例子，比如class<T> class<?> 这些泛型的用法。会用这个类，可能对抽象有更好的理解
 *
 */
public class CaseForClass {
    private static CaseForClass sCase;

    public static CaseForClass obtain() {
        if (sCase == null) {
            sCase = new CaseForClass();
        }
        return sCase;
    }

    public void work() {
        Class mClass = ClassCase.class;
        // 下边准备实验一下mClass的那些函数,等..
        // mClass.newInstance();
        // mClass.cast(obj)
        // mClass.getFields()
        // mClass.getDeclaredFields()
    }

    // 用于作实验的类
    public static class ClassCase {

    }
}
