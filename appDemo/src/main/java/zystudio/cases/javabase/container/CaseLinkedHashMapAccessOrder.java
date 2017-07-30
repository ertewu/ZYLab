package zystudio.cases.javabase.container;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import zystudio.mylib.utils.LogUtil;

/**
 * 试一下这个文章的例子：
 * http://www.cnblogs.com/yejg1212/archive/2013/04/01/2992921.html
 */
public class CaseLinkedHashMapAccessOrder {

    public void work() {

        Map<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "e");

        for (Iterator<String> iterator = map.values().iterator(); iterator
                .hasNext(); ) {
            String name = (String) iterator.next();
            LogUtil.log("NoGet Order:" + name);
        }

        //new add
        map.get("1");
        map.get("2");

        for (Iterator<String> iterator = map.values().iterator(); iterator
                .hasNext(); ) {
            String name = (String) iterator.next();
            LogUtil.log("AfterGet Order:" + name);
        }

    }
}

