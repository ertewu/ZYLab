package zystudio.cases.dataprocess.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import zystudio.mylib.utils.LogUtil;

/**
 * 这是关于URLEncoder类的使用的函数，也是因为下边网址的最后一句话来试试的
 * http://snmoney.blog.163.com/blog/static/440058201462911229572/
 * 这个意思是：
 * 先把数据base64,这里边有+,/，然后url编码...，这样+,/都变成％这样的?
 * 这个方法很垃圾，还是那个用变种的base64编码在url上比较好
 */

public class CaseURLEncoder {

    private static CaseURLEncoder sCase;
    private static final String[] LIST = { "你好+", "abc" ,"http://sohu.com"};

    public final static CaseURLEncoder obtain() {
        if (sCase == null) {
            sCase = new CaseURLEncoder();
        }
        return sCase;
    }

    public void work() {
            try {
                for(String str:LIST){
                    String urlMsg=URLEncoder.encode(str,"UTF-8");
                    LogUtil.log("encoding is:"+urlMsg);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
 //结果是：
//            10-16 17:04:01.978: I/ZYStudio(8227): encoding is:%E4%BD%A0%E5%A5%BD%2B
//            10-16 17:04:01.978: I/ZYStudio(8227): encoding is:abc
//            10-16 17:04:01.978: I/ZYStudio(8227): encoding is:http%3A%2F%2Fsohu.com
    }
}
