package zystudio.cases.dataprocess;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import zystudio.mylib.utils.LogUtil;

//Demo from :http://bbs.it-home.org/thread-2577-1-1.html
//这篇文章也不错：http://blog.csdn.net/bage1988320/article/details/6832940
public class CaseMD5Digest {

    private static CaseMD5Digest sCase;

    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f' };

    /**
     * 想要构造的例子是file1与file2文件名不同内容相同，file1与file3内容和文件名都不相同,还有一个压缩包path
     */
    //    private final String FILE_PATH_1, FILE_PATH_2, FILE_PATH_3, FILE_ZIP_PATH;
    private static final String DIR_PATH="/storage/sdcard0/MyDir/";
    private final String PATH_LIST[]={
            DIR_PATH+"text1.java",
            DIR_PATH+"text1_cp.java",
            DIR_PATH+"text2.xml",
            DIR_PATH+"md5_zip.zip",
    };


    protected static MessageDigest sMessageDigest = null;
    private static String sAlgorithm="MD5";
//    private static String mAlgorithm="sha-1";

    static {
        try {
//            sMessageDigest = MessageDigest.getInstance("MD5");
            sMessageDigest = MessageDigest.getInstance(sAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static CaseMD5Digest obtain() {
        if (sCase == null) {
            sCase = new CaseMD5Digest();
        }
        return sCase;
    }

    public void work() {
        try {
            LogUtil.printTime("begin time");
            for(int i=0;i<PATH_LIST.length;i++){
                String path=PATH_LIST[i];
                File file = new File(path);
                String md5Str=getFileMD5String(file);
                LogUtil.printTime("halfway "+i+" comsume time");
                LogUtil.log("i  "+sAlgorithm+"  is:"+md5Str);
            }

            /**
             * 结论是文件内容一样的话，不管文件名是否相同，其md5值是相同的..
             * 时间方面，在i9100上40多m的zip文件生成md5也只用了1.2秒,sha-1用了不到2秒，还是很快的...
             *
             * 还有我发现cm文件管理器是有一个计算校验和的功能，和我算的一模一样，实验结束
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        sMessageDigest.update(bytes);
        return bufferToHex(sMessageDigest.digest());
    }

    public static String getFileMD5String(File file) throws Exception {
        InputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            sMessageDigest.update(buffer, 0, numRead);
        }
        fis.close();
        //digest函数返回的也是byte数组,要把这个数组转换成字组串，才能成为我所看出来的校验和，也即是那个hash value
        return bufferToHex(sMessageDigest.digest());
    }

    //Private methods

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}
