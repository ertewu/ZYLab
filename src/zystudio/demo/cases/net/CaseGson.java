package zystudio.demo.cases.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

public class CaseGson {

    private static final String SKIN_URL = "http://10.129.156.208/SkinDemo/skin.php";
    private static CaseGson sCase;
    private Context mCtx;

    public static CaseGson obtain(Context ctx) {
        if (sCase == null) {
            sCase = new CaseGson();
            sCase.init(ctx);
        }
        return sCase;
    }

    private void init(Context ctx) {
        mCtx = ctx;
    }

    public void work() {
        fetchJsonAsync();
    }

    /**
     * very good 这个case写得很好，直接通过
     */
    private void fetchJsonAsync() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                fetchJsonByHttpConnection();
            }

        }).start();
    }

    private void fetchJsonByHttpConnection() {
        try {
            URL url = new URL(SKIN_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.connect();
            int state = conn.getResponseCode();
            switch (state) {
            case HttpURLConnection.HTTP_OK:
                InputStream stream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(
                        stream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                Log.i("ertewu", "char stream:" + sb.toString());
                parseJsonTradition(sb.toString());
                break;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 用google的jar包Gson解析json
     */
    private void parseJsonByGson(String str) {
        Gson gson = new Gson();
    }

    /**
     * Android API传统方法解析json,是以下边的示例参照的<br>
     * http://blog.csdn.net/love__coder/article/details/6765720
     */
    private void parseJsonTradition(String str) {
        try {
            ArrayList<SkinBean> list = new ArrayList<SkinBean>();
            JSONArray jarray = new JSONArray(str);
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject obj = (JSONObject) jarray.get(i);
                SkinBean bean = new SkinBean(obj);
                Log.i("ertewu", bean.toString());
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static class SkinBean {
        static final String FIELD_INDEX = "index";
        static final String FIELD_NAME = "name";
        static final String FIELD_IMAGE_URL = "imageUrl";
        static final String FIELD_THUMBNAIL_URL = "previewUrl";

        private String mIndex;
        private String mName;
        private String mImageUrl;
        private String mThumbNailUrl;

        public SkinBean(JSONObject obj) {
            try {
                mIndex = obj.getString(FIELD_INDEX);
                mName = obj.getString(FIELD_NAME);
                mImageUrl = obj.getString(FIELD_IMAGE_URL);
                mThumbNailUrl = obj.getString(FIELD_THUMBNAIL_URL);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "index:" + mIndex + "|name:" + mName + "|imageUrl:"
                    + mImageUrl + "|thumbNailUrl:" + mThumbNailUrl;
        }
    }

}
