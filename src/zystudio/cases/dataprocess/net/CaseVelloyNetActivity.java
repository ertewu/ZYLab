package zystudio.cases.dataprocess.net;

import org.json.JSONObject;

import zystudio.demo.R;
import zystudio.mylib.utils.LogUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CaseVelloyNetActivity extends Activity {

    private static final String SIMPLE_URL = "http://farm1.static.flickr.com/114/298125983_0e4bf66782_b.jpg";
    private static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private ProgressDialog mProgressDialog;
    private Button startBtn;
    private String mSkinFile;
    private String mUrl;

    public static void start(Activity act) {
        Intent intent = new Intent(act, CaseVelloyNetActivity.class);
        act.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casefordownload_activity);
        mSkinFile = getFilesDir().getAbsolutePath() + "/" + System.currentTimeMillis() % 10000
                + ".9.png";
        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchTextDemo();
            }
        });
    }

    private void fetchImgDemo() {
        mUrl = SIMPLE_URL;
        RequestQueue queue = Volley.newRequestQueue(this);
    }

    private void fetchJsonDemo() {
        mUrl = null;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, mUrl,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    private void fetchTextDemo() {
        mUrl = "http://www.baidu.com";
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
}