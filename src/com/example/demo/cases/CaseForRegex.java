package com.example.demo.cases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;
import android.util.Log;

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

    private boolean isSohu1(String host) {
        String regex = "((\\w+\\.)?)(sohu\\.com)((\\.\\w+)?)";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(host);
        boolean b = m.matches();
        return b;
    }

    private boolean isSohu2(String host) {
        String regex = "((.*\\.)|)sohu\\.com(|(\\..*))";
        return host.matches(regex);
    }

    private boolean isSohu3(String host) {
        String regex = "(([\\s\\S]*\\.)|)sohu\\.com(|(\\.[\\s\\S]*))";
        return host.matches(regex);
    }
}
