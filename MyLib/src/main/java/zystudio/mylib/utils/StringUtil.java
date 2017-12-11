package zystudio.mylib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class StringUtil {
    /**
     * Check if the string contains blank characters. 空白字符包括：回车，tab，什么的..
     * 
     * @param str
     *            the String to be checked
     * @return true if it contains the blank characters.Other else false.
     * @author WZY
     */
    public static boolean isStrBlank(String str) {
        if (str == null)
            return true;

        Pattern p = Pattern.compile("^\\s*$");
        Matcher titleMatcher = p.matcher(str);
        if (titleMatcher.find())
            return true;

        return false;
    }

    public static String getHttpFileName() {
        String url = "http://www.example.com/some/path/to/a/file.xml";

        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());

        String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));

        Log.i("ertewu", fileName);
        Log.i("ertewu", fileNameWithoutExtn);

        return fileName;
    }
}
