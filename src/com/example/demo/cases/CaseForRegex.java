package com.example.demo.cases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;
import android.util.Log;

/**
 * 这个demo的需求是匹配任何包含sohu.com的URL，因为事先已知输入为URL，所以不用判断是否为URL。<br>
 * 
 */
public class CaseForRegex {

    static String URL_1 = "http://news.sohu.com/20140220/n395324798.shtml";
    static String URL_2 = "http://www.sohu.com/20140220/n395324798.shtml";
    static String URL_3 = "http://sohu.com/20140220/n395324798.shtml";
    static String URL_4 = "file:///sohu.com/20140220/n395324798.shtml";
    static String URL_5 = "http://sohu.com.cn/20140220/n395324798.shtml";
    static String URL_6 = "http://asohu.comb.cn/20140220/n395324798.shtml";
    static String URL_7 = "http://soHu.com.cn/20140220/n395324798.shtml";

    private static CaseForRegex sCase;

    public static CaseForRegex obtain() {
        if (sCase == null) {
            sCase = new CaseForRegex();
        }
        return sCase;
    }

    public void work() {
        logHostName(URL_1);
        logHostName(URL_2);
        logHostName(URL_3);
        logHostName(URL_4);
        logHostName(URL_5);
        logHostName(URL_6);
        logHostName(URL_7);
    }

    private void logHostName(String urlstr) {
        try {
            URL url = new URL(urlstr);
            String host = url.getHost();

            if (!TextUtils.isEmpty(host)) {
                Log.i("ertewu", "host is:" + host);
                boolean result = isSohu1(host);
                // boolean result = isSohu2(host);
                // boolean result = isSohu3(host);
                Log.i("ertewu", "result is:" + result + "\n"
                        + "------------------");
            } else {
                Log.i("ertewu", "host is null" + "\n" + "--------------");
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 这个正则呢:<br>
     * w表示[a-zA-Z0-9]这样的单个字符，显示这个缺少下划线这样的重量级字符<br>
     * +表示至少有一个前边的正则表达式<br>
     * ?表示0个或一个前边的正则表达式<br>
     * 
     * 显示 isSohu1比 isSohu2,isSohu3 所匹配的集合更少了..如果isSohu2还能实现功能的话，isSohu1估计够呛了..
     * 关于java里用正则要好多斜杠的问题，是怎么回事?
     */
    private boolean isSohu1(String host) {
        String regex = "((\\w+\\.)?)(sohu\\.com)((\\.\\w+)?)";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(host);
        boolean b = m.matches();
        return b;
    }

    /**
     * 这个正则呢:<br>
     * '.'匹配除换行符\n之外的任何单个字符<br>
     * '*'表示0个或多个前边的正则表达式<br>
     * 那么其实 '.*' 匹配了任何不带有\n的字符串 <br>
     * '.*\\. '其实匹配了任何尾是.的不带有\n的字符串<br>
     * 
     * 那么其实isSohu2这个函数，是比isSohu3所包含的集合要少的
     */
    private boolean isSohu2(String host) {
        String regex = "((.*\\.)|)sohu\\.com(|(\\..*))";
        return host.matches(regex);
    }

    /**
     * 这个正则的意思就是<br>
     * 要不就是: sohu.com<br>
     * 要不就是: （这里是任何字符）.sohu.com<br>
     * 要不就是: sohu.com.(这里是任何字符)<br>
     * 要不就是: (这里是任何字符).sohu.com.(这里是任何字符）<br>
     * s 是指任何非空白字符<br>
     * S 是指除s以外的任何字符<br>
     * '*'表示0个或任意多个<br>
     * '\\.' 就是表示要匹配.这个字符，显然这是个转义
     */
    private boolean isSohu3(String host) {
        String regex = "(([\\s\\S]*\\.)|)sohu\\.com(|(\\.[\\s\\S]*))";
        return host.matches(regex);
    }
}
