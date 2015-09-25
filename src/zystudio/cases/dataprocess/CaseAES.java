package zystudio.cases.dataprocess;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import zystudio.mylib.utils.FileUtil;
import zystudio.mylib.utils.LogUtil;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class CaseAES {

    private static final String JSON_NAME="navigation_fix.json";

    private static CaseAES sInstance;

    private Context mContext;

    public static CaseAES getInstance(Context ctx){
        if(sInstance==null){
            sInstance=new CaseAES(ctx);
        }
        return sInstance;
    }

    private CaseAES(Context ctx){
        mContext=ctx;
    }
    /**
     * 密钥, 初始向量数组
     */
    private static final String[][] KEY_IV = {
        /**
         * 密钥, 长度256
         */
        {"vc8v7vghw7v278vn2v8239vh29vh890m", "aqkeezgxijvlm2op"},
        {"vc8v7vghw7v278vn2v8239vh29vh890m", "aqkeezgxijvlm2op"}
    };

    public void showCase(){
//        checkEncrytCase();
        exportProcessedData();
    }

    private void exportProcessedData(){
       //要生成预置数据,是json的byte流,先zip压缩,再加密的流程,然后再导出到文件上
        try{
            AssetManager manager=mContext.getAssets();
            InputStream is=manager.open(JSON_NAME);
            int size=is.available();
            byte[] buf=new byte[size];
            //从asset中读入明文byte流
            is.read(buf);
            is.close();
            //zip压缩
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            Deflater compressDeflater=new Deflater(Deflater.DEFAULT_COMPRESSION,true);
            DeflaterOutputStream dout=new DeflaterOutputStream(baos,compressDeflater);
            dout.write(buf);
            dout.finish();
            byte[] compressBuf=baos.toByteArray();
            //压缩之后再加密
            byte[] encryptBuf=encrypt(compressBuf);
            //输出到文件
            FileUtil.exportToFileWithFOStream("/sdcard/0_MyDir/yuzhi.dat", encryptBuf);
        }catch(Exception e){
            LogUtil.log("exportProcessedData excption:"+e.getMessage());
        }finally{
            //TODO:这里本该关闭很多stream之类的,但是我连声明都写在try里边了,也就不好弄了,主要是懒得弄了
        }
    }


    private void checkEncrytCase(){
        try {
            AssetManager manager=mContext.getAssets();
            InputStream is=manager.open(JSON_NAME);
            int size=is.available();
            byte[] buf=new byte[size];
            //从asset中读入明文byte流
            is.read(buf);
            is.close();
            //扰流加密
            byte[] encryptByte=encrypt(buf);
//            尝试解密刚才加密过的byte流, log显示数据正确实验成功
//            String decryptStr=decyptToStr(encryptByte);
//            LogUtil.log(decryptStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void compressAndExport(byte[] content){

    }

    /**
     * 第一个,第二个链接　讲加密模式讲得特别好，　第三个链接讲填充模式讲得不错　
     * 常规中，我们就用cbc的加密模式，PKCS5Padding的加密模式了
     * CBC的加密模式就需要ＩＶ 初始向量了
     * 
     *http://wenku.baidu.com/view/01114e8683d049649b665801.html 
     * http://blog.poxiao.me/p/advanced-encryption-standard-and-block-cipher-mode/
     * http://blog.sina.com.cn/s/blog_679daa6b0100zmpp.html 
     */
    private byte[] encrypt(byte[] plainTextByte){
        try{
            String key=KEY_IV[0][0];
            String iv=KEY_IV[0][1];
            SecretKeySpec keySpec=new SecretKeySpec(key.getBytes(),"AES");
            IvParameterSpec ivSpec=new IvParameterSpec(iv.getBytes());
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec,ivSpec);
            byte[] result=cipher.doFinal(plainTextByte);
            return result;
        }catch(Exception e){
        }
        return null;
    }

    private byte[] decrypt(byte[]  cipherBytes){
        try{
            String key=KEY_IV[0][0];
            String iv=KEY_IV[0][1];
            SecretKeySpec keySpec=new SecretKeySpec(key.getBytes(),"AES");
            IvParameterSpec ivSpec=new IvParameterSpec(iv.getBytes());
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec,ivSpec);
            byte[] result=cipher.doFinal(cipherBytes);
            return result;
        }catch(Exception e){
        }
        return null;
    }

    private String decyptToStr(byte[] cipherBytes){
        String decryptStr="";
        try {
            byte[] decryptBytes=decrypt(cipherBytes);
            if(!(decryptBytes==null||decryptBytes.length==0)){
                decryptStr=new String(decryptBytes,"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decryptStr;
    }

}
