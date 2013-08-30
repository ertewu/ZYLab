package com.example.demo.cases;

import org.json.JSONArray;

import android.text.TextUtils;

public class CaseForJson {

    //金锋的意思是，虽然没有字典，但仍然是JSON，最好用JSON库去构成，不要自己去拼..
    public static String assembleMsgIdArrayStr(String msgIdStr){
        if(!TextUtils.isEmpty(msgIdStr)){
            JSONArray msgIdArrayStr=new JSONArray();
            msgIdArrayStr.put(msgIdStr);
            return msgIdArrayStr.toString();
        }
        return null;
    }
}
