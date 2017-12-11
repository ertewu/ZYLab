package zystudio.cases.prepare;

import android.util.Log;

import java.nio.ByteBuffer;

import zystudio.mylib.utils.LogUtil;

/**
 * 此类主要用于实验ByteBuffer的使用方法，这里边有很多的方法，我经常忘记是怎么用的
 */
public class CaseBufferAllocate {

    private static CaseBufferAllocate sCase;

    public static CaseBufferAllocate obtain() {
        if (sCase == null) {
            sCase = new CaseBufferAllocate();
        }
        return sCase;
    }

    private ByteBuffer mBuf;
    private CaseBufferAllocate(){
        //ByteBuffer 是没有构造函数的
//       mBuf=new ByteBuffer();
        mBuf=ByteBuffer.allocate(10);
    }

    public void work() {
//        testBufPut();
//        testBufLimit();
        testReadMode();
    }

    private void testBufPut(){
        LogUtil.log("Buf property r29:"+mBuf.position()+"|"+mBuf.limit());
        //在java中 一个 char 占2个字节(中文char和英文char都是2位）,因为java是unicode码，是16位的，
        //当然以后写入时，是另外一码事
//        mBuf.putChar('a');
        mBuf.putChar('王');
        LogUtil.log("Buf property r31:"+mBuf.position()+"|"+mBuf.limit());

        // +-127是对的，+-128就是错的，说明用Int表示的byte，就只能这么大了
        byte mByte=127;
//        byte mByte=128;
        byte mByte2=-127;
//        byte mByte2=-128;

        mBuf.put(mByte);
        LogUtil.log("Buf property r41:"+mBuf.position()+"|"+mBuf.limit());
        mBuf.clear();
    }

    private void testBufLimit(){
        try{
            mBuf.limit(5);
            LogUtil.log("Buf property r55:"+mBuf.position()+"|"+mBuf.limit()+"|"+mBuf.capacity());
            mBuf.putChar('b');
            mBuf.putChar('c');
            mBuf.putChar('d');
//            mBuf.putChar('e');
        } catch (Exception e){
            /*
             * 打出了 bufferOverflowException: java.nio.BufferOverflowException
             * 但是这个exception也挺无语的，，detailMessage为null..
             * 报出之后的情况，从debug来看是 position=4，limit=5,capacity=10
             */
            e.printStackTrace();
        }
        mBuf.clear();
    }

    private void testReadMode(){
        mBuf=ByteBuffer.allocate(10);
        LogUtil.log("Buf property r76:"+mBuf.position()+"|"+mBuf.limit()+"|"+mBuf.capacity());
        byte[] bufs= new byte[]{10,11,12,13};
        for(byte buf:bufs){
            mBuf.put(buf);
            LogUtil.log("Buf property loop:"+mBuf.position()+"|"+mBuf.limit()+"|"+mBuf.capacity());
        }
        byte readBuf;
//        readBuf=mBuf.get();
//        LogUtil.log("readBuf r80:"+readBuf);
//        readBuf=mBuf.get();
//        LogUtil.log("readBuf r82:"+readBuf);

        //不flip ，跟本读不出来啥， 上边读出来的全是0，0..
        mBuf.flip();
        readBuf=mBuf.get();
        LogUtil.log("readBuf r87:"+readBuf);
        LogUtil.log("Buf property r89:"+mBuf.position()+"|"+mBuf.limit()+"|"+mBuf.capacity());
        readBuf=mBuf.get();
        LogUtil.log("readBuf r89:"+readBuf);
        //limit为什么是6，我就不理解了
        //为什么是6，跟flip前的两次get有关系，没有这两次get，limit就是4，作为readMode，就是正常
        LogUtil.log("Buf property r93:"+mBuf.position()+"|"+mBuf.limit()+"|"+mBuf.capacity());
    }

}
