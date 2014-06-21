package com.example.demo.cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.util.Log;

/**
 * 这个是用来学习Java中的container的case，所谓container，我的理解至少是collection与Map
 * 这里跟着JAVA编程思想的container in deep 那章来走<br>
 * 这里有一个参考：http://txy821.iteye.com/blog/760357
 */
public class CaseJavaContainer {

    private static CaseJavaContainer sCase;

    public static CaseJavaContainer obtain() {
        if (sCase == null) {
            sCase = new CaseJavaContainer();
        }
        return sCase;
    }

    public void work() {
        testCollectionFillpkNCopy();
        // testCollectionCopy();
        // testCollectionsReplaceAll();
        // testArraysCase();
    }

    // 我这个没体现出问题来..,这个case不太行
    private void testCollectionFillpkNCopy() {
        // 实验Collection.fill与Collection.ncopy的对比
        String str = "wzy";
        StrWrapper wrapper = new StrWrapper();
        wrapper.mStr = str;

        List<StrWrapper> toFillList = new ArrayList<StrWrapper>();
        for (int i = 0; i < 5; i++) {
            wrapper.mStr = str + i;
            toFillList.add(wrapper);
        }
        Collections.fill(toFillList, wrapper);
        for (StrWrapper item : toFillList) {
            Log.i("ertewu", item.hashCode() + "");
        }

        Log.i("ertewu", "------------------------------");

        List<StrWrapper> toCopyList = Collections.nCopies(5, wrapper);
        for (StrWrapper item : toCopyList) {
            Log.i("ertewu", item.hashCode() + "");
        }
    }

    private class StrWrapper {
        public String mStr;
    }

    private void testCollectionCopy() {
        String numStr = "one two three four five one seven";
        String phoneNames = "nokia apple moto ";
        // String phoneNames = "nokia apple moto google sony samsung lg xiaomi";
        String[] numArray = numStr.split(" ");
        String[] phoneArray = phoneNames.split(" ");
        List<String> numList = Arrays.asList(numArray);
        List<String> phoneList = Arrays.asList(phoneArray);

        Collections.copy(numList, phoneList);
        // 如果是3个那个最终结果是numList的前几个被替换了..
        // 如果是8个那个，那会挂的,这个asList限制还是真多啊..
        Log.i("ertewu", numList + "");
    }

    /**
     * Collections是针对集合类的帮助类,实现了一系列静态方法实现了对各种集合的排序、搜索和线程安全等操作<br>
     * 主要实现下边几个方法的使用:<br>
     * Collections.copy(destination, source);<br>
     * Collections.fill(list, object) <br>
     * Collections.nCopies(length, object) Collections.replaceAll
     */
    private void testCollectionsReplaceAll() {
        String numStr = "one two three four five one seven";
        String[] numArray = numStr.split(" ");
        List<String> numList = Arrays.asList(numArray);

        // 底下这句话不行的原因是因为Arrays.asList返回的是一个固定大小数组，不能再add了，没想到List还有这种事..
        // numList.add("one");
        // replace all 就是把某个字符串替换
        Collections.replaceAll(numList, "one", "wzy");
        Log.i("ertewu", numList + "");
    }

    /**
     * 实验 Arrays类中的静态方法,如：<br>
     * Arrays.asList()
     */
    private void testArraysCase() {
        String numStr = "one two three four five six seven";
        String[] numArray = numStr.split(" ");
        // 明明就是arrayList,但是就是不能直接写，必须得这样写么..
        List<String> numList = Arrays.asList(numArray);
        for (String str : numArray) {
            Log.i("ertewu", str);
        }
        Log.i("ertewu", numList + "");
    }

}
