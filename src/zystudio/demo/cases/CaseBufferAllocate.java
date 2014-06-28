package zystudio.demo.cases;

/**
 * 主要是为了实验java glSurfaceView中使用的 <br>
 * ByteBuffer.allocateDirect()order(ByteOrder.nativeOrder()).asFloatBuffer();
 * 这种用法
 */
public class CaseBufferAllocate {

    private static CaseBufferAllocate sCase;

    public static CaseBufferAllocate obtain() {
        if (sCase == null) {
            sCase = new CaseBufferAllocate();
        }
        return sCase;
    }

    public void work() {

    }
}
