package zystudio.cases.javabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import zystudio.mylib.utils.LogUtil;

/**
 * Shallow Copy, Deep Copy 的Case,以及clone函数的理解 <{@link # CaseShallowCopy.java} <
 * {@link # CaseDeepCopy.java}
 *
 * 第二种deep copy的方法，用序列化serialization去实现deep copy
 * http://www.jusfortechies.com/java/core-java/deepcopy_and_shallowcopy.php
 */
public class CaseDeepCopySerializable {

    private static CaseDeepCopySerializable sCase;

    public static CaseDeepCopySerializable obtain() {
        if (sCase == null) {
            sCase = new CaseDeepCopySerializable();
        }
        return sCase;
    }

    public void work() {
        try {
            ObjectOutputStream oos=null;
            ObjectInputStream ois=null;

            ColoredCircle c1=new ColoredCircle(100,100);
            LogUtil.log("Original ="+c1);
            ColoredCircle c2=null;
            //Deep copy
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(bos);
            oos.writeObject(c1);
            oos.flush();

            ByteArrayInputStream bin=new ByteArrayInputStream (bos.toByteArray());
            ois=new ObjectInputStream(bin);
            c2=(ColoredCircle) ois.readObject();

            c1.setX(200);
            c1.setY(200);
            LogUtil.log("Original after update: ="+c1);
            LogUtil.log("Copied: ="+c2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //显然序列号要求ColoredCircle的成员们类型 也都可以序列化
    public class ColoredCircle implements Serializable {
        private int x;
        private int y;

        public ColoredCircle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int X) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
