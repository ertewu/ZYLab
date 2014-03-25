package com.example.demo.cases.prepare;

import java.util.HashMap;
import java.util.LinkedHashMap;

import android.content.Context;

import com.util.LogUtils;

/**
 * 这个数据结构具有比较重要的意义，但我并不太很上手，有空我研究一下吧： http://zhangshixi.iteye.com/blog/673789
 * LinkedHashMap 能够保持开始进入的顺序,当然也可以自己赋顺序
 */
public class CaseForLinkedHashMap {

    private static CaseForLinkedHashMap sCase;
    private Context mContext;

    public static CaseForLinkedHashMap instance(Context context) {
        if (sCase == null) {
            sCase = new CaseForLinkedHashMap();
            sCase.init(context);
        }
        return sCase;
    }

    private void init(Context context) {
        this.mContext = context;
    }

    public void work() {
        showLinkedHashMap();
        showHashMap();
    }

    /** 这个打出来的，就是put的顺序,当然这里边是linked，作为链表可能多少有点效率低吧 */
    private void showLinkedHashMap() {
        LinkedHashMap<Integer, String> Lhm = new LinkedHashMap<Integer, String>();
        Lhm.put(1, "Gyan");
        Lhm.put(6, "Ankit");
        Lhm.put(5, "Arun");
        Lhm.put(4, "Anand");
        Lhm.put(3, "Ram");
        LogUtils.log("The Entries of LinkedHashMap are : " + Lhm);
    }

    /** 这个打出来的，不一定是put的顺序，事实上很少是put的顺序，也就是说里边是没有顺序的 */
    private void showHashMap() {
        HashMap<Integer, String> Lhm = new HashMap<Integer, String>();
        Lhm.put(1, "Gyan");
        Lhm.put(6, "Ankit");
        Lhm.put(5, "Arun");
        Lhm.put(4, "Anand");
        Lhm.put(3, "Ram");
        LogUtils.log("The Entries of HashMap are : " + Lhm);
    }

}
