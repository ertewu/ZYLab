package zystudio.cases.javabase;

import android.content.Context;

import java.nio.charset.Charset;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by wzy on 16-7-18.
 * http://stackoverflow.com/questions/9825283/java-char-vs-string-byte-size
 * 这里的意思是 char是一个字符的unicode编码, 在内存中一直是占两个字节的 </n>
 * 而String.getBytes()是说 String在以缺省的具体字符编码(比如utf-8)输出时,其要输出的的字节大小..</br>
 *
 * 那我现在想的是,网络传输时,是传的纯unicode编码,还是具体字符编码utf-8呢?
 *
 * http://stackoverflow.com/questions/5078314/isnt-the-size-of-character-in-java-2-bytes
 * 这个解释得也入理
 *
 * http://www.zhihu.com/question/23374078
 * 这里边解释了为什么没有用unicode字符集直接定长编码的问题,就是因为英文明明可以只用一个字节,却要用多个字节造成了存储的比较大浪费
 */
public class CaseCharEncode {

    private Context mContext;
    public CaseCharEncode (Context context){
        mContext=context;
    }

    public void showCase() {
        char ch1='a';
        char ch2='王';

        String str="a";
        LogUtil.log("bytes size is:"+str.getBytes(Charset.forName("utf-8")).length);
        str="王";
        LogUtil.log("bytes size is:"+str.getBytes(Charset.forName("utf-8")).length);
        str="a王";
        LogUtil.log("bytes size is:"+str.getBytes(Charset.forName("utf-8")).length);

    }
}
