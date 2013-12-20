package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {


    /**
     * Check if the string contains blank characters.
     * 空白字符包括：回车，tab，什么的..
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
}
