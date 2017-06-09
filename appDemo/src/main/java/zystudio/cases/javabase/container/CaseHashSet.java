package zystudio.cases.javabase.container;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.HashSet;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by wzy on 16-4-19.
 * Test:http://www.codes51.com/article/detail_163576.html
 * 这里，我对hashmap的链地址解决hash冲突是理解的，
 * 我对spareArray与ＡrrayMap也大体上理解，但是有一个细节并不理解
 * 就是SpareArray与ＡrrayMap的key与value是如何对应的..
 * 表面的感受是，如果用二分法，那value的array，就是存在顺序的,那每次put新内容时，array里边也重新排序？就是这里不懂了..
 *
 * Test2:
 * http://www.trinea.cn/android/hashmap-loop-performance/
 * 至于这个,1,2,4显然是一种方式，自然内容也是一种方式．
 * 第３呢，显然，就算一样的遍历方式，还多了一次从key取map，而且一定会全部遍历的，必然是不好的，这么sb的方式，楼主还写测试无聊了..
 */
public class CaseHashSet {

    //java.util.HashMap
    private HashSet<String> mSet;

    public CaseHashSet(){
        mSet=new HashSet<String>();
    }

    public void work(){
        boolean result=mSet.add("123");
        LogUtil.log("result is:"+result);
        result=mSet.add("123");
        LogUtil.log("result is:"+result);
    }

}
