package zystudio.cases.dataprocess.net;

import java.nio.charset.Charset;

import zystudio.demo.R;
import zystudio.mylib.utils.FileUtil;
import zystudio.mylib.utils.LogUtil;
import zystudio.utils.ByteUtil;
import zystudio.utils.CompressionUtils;
import zystudio.utils.CryptoUtils;
import zystudio.utils.Util;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 *这个教程很好
 * http://blog.csdn.net/guolin_blog/article/details/17482165
 */

public class CaseVelloyNetActivity extends Activity {

    private static final String SIMPLE_URL = "http://farm1.static.flickr.com/114/298125983_0e4bf66782_b.jpg";
    private static final String SIMPLE_URL2 = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
    private static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private ProgressDialog mProgressDialog;
    private Button startBtn;
    private String mSkinFile;
    private String mUrl;
    private ImageView mImgView;

    public static void start(Activity act) {
        Intent intent = new Intent(act, CaseVelloyNetActivity.class);
        act.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casevelloy_activity);
        mImgView = (ImageView) findViewById(R.id.img);
        mSkinFile = getFilesDir().getAbsolutePath() + "/" + System.currentTimeMillis() % 10000 + ".9.png";
        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchSunBoInterfaceDemo();
                //            	if(System.currentTimeMillis()%2==0){
                ////            		fetchTextDemo();
                //            		fetchImgDemo();
                //            	}else{
                //            		fetchImgDemo();
                //            	}
                //                fetchImgImageLoader();
            }
        });
    }
    private byte[] deflateAndEncryptData(byte[] data) {
        try {
            byte[] defaltedData = CompressionUtils.compress(data);
            byte[] defaltedAndEncryptData = CryptoUtils.encrypt(defaltedData);
            return defaltedAndEncryptData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ByteUtil.EMPTY_BYTES;
    }


    private void fetchImgImageLoader() {
        RequestQueue queue = Volley.newRequestQueue(this);
        // 这个就是没有cache而已
        // ImageLoader imgLoader = new ImageLoader(queue, new ImageCache() {
        // @Override
        // public Bitmap getBitmap(String url) { return null; }
        // @Override
        // public void putBitmap(String url, Bitmap bitmap) { }
        // });
        ImageLoader imgLoader = new ImageLoader(queue, new BitmapCache());
        ImageListener listener = ImageLoader.getImageListener(mImgView, R.drawable.default_sign, R.drawable.error_sign);
        imgLoader.get(SIMPLE_URL2, listener, 200, 200);
    }

    private void fetchImgDemo() {
        mUrl = SIMPLE_URL;
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageRequest request = new ImageRequest(mUrl,
                /** <br> */
                new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                mImgView.setImageBitmap(response);
                Util.showMsg("VolleyImageDownload occured");
            };
        }, /** <br> */
        0, 0, ScaleType.CENTER, Config.RGB_565,
        /** <br> */
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.showMsg("VolleyImageError occured");
            }
        });
        queue.add(request);
    }

    private void fetchSunBoInterfaceDemo() {
        //        mUrl = "http://www.baidu.com";
        final String mUrl="http://data.mse.sogou.com/batchQuicklaunch.php?h=ffffffff-c5bf-052a-ef97-390c0033c587&r=0000&v=3.3.0&hv=AOSP+on+Mako&pv=ANDROID4.4.4&scale=3";
        final String jsonStr=getResources().getString(R.string.demo_content);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.POST, mUrl,
                new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                LogUtil.log("onResponse:" + response);
                byte[] processedData=deflateAndEncryptData(response.getBytes());
                FileUtil.exportToFileWithFOStream("/sdcard/sunbo_encrypt", processedData);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.log("onErrorResponse:" + error.getMessage());
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return jsonStr.getBytes(Charset.forName("UTF-8"));
                //                return super.getBody();
            }
        };
        queue.add(strRequest);
    }

    private void fetchTextDemo() {
        //        mUrl = "http://www.baidu.com";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.GET, mUrl,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtil.log("onResponse:" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.log("onErrorResponse:" + error.getMessage());
            }
        });
        queue.add(strRequest);
    }

    private static class BitmapCache implements ImageCache {
        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }
}