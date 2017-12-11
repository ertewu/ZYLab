package zystudio.cases.dataprocess.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import zystudio.mylib.utils.LogUtil;
import android.util.Base64;

/**
 * 比较靠谱点网址： http://zh.wikipedia.org/wiki/Base64
 * http://blog.csdn.net/xuefeng0707/article/details/19845111
 *
 *==============
 *
 *关于 Android的Base64的的flag问题，也找了几个网址：
 *
 *http://blog.sina.com.cn/s/blog_649451f901015cor.html
 *
 *http://www.th7.cn/Program/Android/201407/232576.shtml
 *
 *http://snmoney.blog.163.com/blog/static/440058201462911229572/
 *
 *WIKI中文上也有说，电子邮件的rfc822 base64标准规定，每76个字符还需要加上一个回车换行，flag default就是这样的
 */
public class CaseBase64 {

    private static CaseBase64 sCase;

    private static final String DIR_PATH = "/storage/sdcard0/MyDir/";
    private final String PATH_LIST[] = { DIR_PATH + "text1.java", DIR_PATH + "text1_cp.java",
            DIR_PATH + "text2.xml", DIR_PATH + "md5_zip.zip", };

    private final String ENCRYPTION_LIST[] = { "abc", "12345", "http://qq.com" };

    // private final String DECRYPTION_LIST[] = new String[3];

    public final static CaseBase64 obtain() {
        if (sCase == null) {
            sCase = new CaseBase64();
        }
        return sCase;
    }

    public void work() {

        performFile();
        // performString();
    }

    /**
     * http://stackoverflow.com/questions/9579874/out-of-memory-when-encoding-
     * file-to-base64
     * 大型文件打成Base64编码成字符串，从上边链接上看就是每3byte可以生成4byte的base64编码，这样一个一个chunk的写入
     * ，就像我底下这样写似的 得出的结论是： 1、40M的文件的base64编码，再写到文件里，在i9100上用了7秒之多
     * 2、base64实际上是byte转byte的过程
     * ，实际情况貌似是encode时3个byte会转成4个byte，这样的话，如果file不是3的整数，这就需要添加零值字节了
     * （零值字节就是后边加bit位为0直到变为3的倍数），然后还要加上=号..,
     *
     * 关于加几个零值字节与=对应的问题，上边的WIKI网址上说得最清楚
     */
    public void performFile() {
        LogUtil.printTime("base64 performFile begin ");
        File file = new File(PATH_LIST[3]);
        InputStream is = null;
        OutputStream outStream = null;
        try {
            is = new FileInputStream(file);
            File outFile = new File(DIR_PATH + "base64encodeFile");
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            outStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[3072];
            while (is.read(buffer) > 0) {
                outStream.write(encrypt(buffer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (outStream != null)
                    outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LogUtil.printTime("base64 performFile end");
    }

    /**
     * 关于Base64的encode的flag，是一个需要注意的东西，但是我现在没有场景去验证这个flag的意义
     * 还有Base64与url的问题：
     */
    public void performString() {
        try {
            for (String str : ENCRYPTION_LIST) {
                String myResult = encryptStr(str);
                String myBackResult = decryptStr(myResult);
                String androidResult = Base64.encodeToString(str.getBytes("UTF-8"), Base64.NO_WRAP);
                LogUtil.log("myResult:" + myResult);
                LogUtil.log("androidResult:" + androidResult);
                LogUtil.log("myBackResult:" + myBackResult);
                LogUtil.log("-----------------------------------------------------------");
            }

            /**
             * 结果符合预期
             */
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * URL的base64是比较麻烦的，WIKI上说得很清楚，只能用另一种改进的Base64
     * 同时我们看到Base64的索引表中，没有一些乱七八糟的字符，只有+,/ 这两种
     */
    public void performUrl(){

    }

    private String encryptStr(String str) {
        // 我想说的是，这里还可以写utf-8以外的么？
        try {
            final String CHAR_SET = "UTF-8";
            byte[] data = str.getBytes(CHAR_SET);
            byte[] result = encrypt(data);
            return new String(result, CHAR_SET);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String decryptStr(String str) {
        final String CHAR_SET = "UTF-8";
        try {
            byte[] data = str.getBytes(CHAR_SET);
            byte[] result = decrypt(data);
            return new String(result, CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] encrypt(byte[] src) {
        byte[] result = Base64.encode(src, Base64.NO_WRAP);
        return result;
    }

    private byte[] decrypt(byte[] src) {
        return Base64.decode(src, Base64.NO_WRAP);
    }
}
