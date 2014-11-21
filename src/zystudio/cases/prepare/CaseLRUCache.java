package zystudio.cases.prepare;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 据说LruCache是以LinkedHashMap为基础作的，那个数据结构有什么其自己特点的用途呢
 * 我猛然发现，我的皮肤模块中，有一个LruCache类，是截图移植那部分的
 *
 * 这个没弄完啊..
 */
public class CaseLRUCache {

    private static CaseLRUCache sCase;
    private Context mCtx;

    public CaseLRUCache getInstance(Context context) {
        if (sCase == null) {
            sCase = new CaseLRUCache(context);
        }
        return sCase;
    }

    private CaseLRUCache(Context context) {
        this.mCtx = context;
    }

    public void work() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        LruCache mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };

        // LruCache是这样初始化的？
        LruCache cache2 = new LruCache<String, Bitmap>(10);
    }

}
