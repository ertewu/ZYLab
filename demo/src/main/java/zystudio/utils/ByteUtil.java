package zystudio.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

import android.text.TextUtils;


public class ByteUtil {

    public static final byte[] EMPTY_BYTES = new byte[0];

    /**
     * MultiGate功能中，子请求Body的结束标志，也可理解为最后一段Body的长度为０
     */
    public static final byte[] MULTIGATE_BODY_END = new byte[] { 0, 0, 0, 0 };

    /**
     * 判断给定byte数据是否为空
     * @param datas 待处理数据
     * @return true: 给定数据为空, false: 给定数据不为空
     */
    public static boolean isEmpty(byte[] datas) {
        return datas == null || datas.length == 0;
    }

    /**
     * 判断2个指定的byte数据是否内容一致
     * @param datas1 待处理数据1
     * @param datas2 待处理数据2
     * @return true: 给定数据一致, false: 给定数据不一致
     */
    public static boolean equals(byte[] datas1, byte[] datas2) {
        if(datas1 == datas2) {
            return true;
        }
        if(datas1 == null || datas2 == null) {
            return false;
        }
        int len1 = datas1.length;
        int len2 = datas2.length;
        if(len1 != len2) {
            return false;
        }
        for (int i = 0; i < len1; i++) {
            if(datas1[i] != datas2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 整数打成４byte数组
     */
    public static byte[] intToBytes(int value) {
        byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
        if (bytes == null) {
            bytes = EMPTY_BYTES;
        }
        return bytes;
    }

    /**
     * 整数打成２byte数组,大端
     */
    public static byte[] intToDoubleByte(int value) {
        if(value<0||value>Short.MAX_VALUE){
            throw new IllegalArgumentException("intToDoubleByte:not correct integer");
        }
        byte[] data = new byte[2];
        data[1] = (byte) (value & 0xFF);
        data[0] = (byte) ((value >> 8) & 0xFF);
        return data;
    }

    /**
     * 整数打成１byte
     */
    public static byte intToSingleByte(int value) {
        byte data = (byte) (value & 0xFF);
        return data;
    }

    /**
     * 整数打成１byte数组
     */
    public static byte[] intToSingleByteList(int value) {
        byte data = (byte) (value & 0xFF);
        return new byte[] { data };
    }

    /*
     * 把byte数组还原回来整数，其中byte数组可能为１位，２位，４位 这个函数都要处理
     */
    public static int byteToInt(byte[] buf) {
        if (buf == null || buf.length == 0) {
            throw new IllegalStateException("Empty byte array can't convert to int");
        }
        if (buf.length == 1) {
            int value = (int) buf[0];
            return value;
        } else if (buf.length == 2) {
            int value = ((buf[0] << 8) & 0xFF00) | (buf[1] & 0xFF);
            return value;
        } else if (buf.length == 4) {
            ByteBuffer wrappedBuf = ByteBuffer.wrap(buf);
            int value = wrappedBuf.order(ByteOrder.BIG_ENDIAN).getInt();
            return value;
        } else {
            throw new IllegalStateException("Error byte array size,can't convert to int");
        }
    }

    public static byte[] strToByte(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.getBytes(Charset.forName("UTF-8"));
        }
        return EMPTY_BYTES;
    }

    public static int getBytesLength(byte[] bytes) {
        if (isEmpty(bytes)) {
            return 0;
        }
        return bytes.length;
    }

    public static byte[] combineBytes(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static byte[] combineMultiBytes(byte[]... bytes) {

        int byteLength = 0;
        for (int i = 0; i < bytes.length; i++) {
            byteLength += bytes[i].length;
        }
        ByteBuffer buf = ByteBuffer.allocate(byteLength);
        for (int i = 0; i < bytes.length; i++) {
            buf.put(bytes[i]);
        }
        return buf.array();
    }

    public static boolean isMultiGateBodyEnd(byte[] byteChunk) {
        if (Arrays.equals(MULTIGATE_BODY_END, byteChunk)) {
            return true;
        }
        return false;
    }
}
