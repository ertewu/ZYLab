package zystudio.cases.dataprocess.net;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import zystudio.demo.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CaseDownloadActivity extends Activity {

    private static final String SIMPLE_URL = "http://farm1.static.flickr.com/114/298125983_0e4bf66782_b.jpg";
    private static final String NINE_PATCH_URL = "http://10.129.156.208/skin.9.png";
    private String mSkinFile;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private Button startBtn;
    private ProgressDialog mProgressDialog;

    public static void start(Activity act) {
        Intent intent = new Intent(act, CaseDownloadActivity.class);
        act.startActivity(intent);
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casefordownload_activity);
        mSkinFile = getFilesDir().getAbsolutePath() + "/" + System.currentTimeMillis() % 10000 + ".9.png";
        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
    }

    private void startDownload() {
        String url = SIMPLE_URL;
        new DownloadFileAsync().execute(url);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DIALOG_DOWNLOAD_PROGRESS:
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Downloading file..");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            return mProgressDialog;
        default:
            return null;
        }
    }

    class DownloadFileAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... aurl) {
            try {

                URL url = new URL(aurl[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) (new URL(
                        NINE_PATCH_URL)).openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    throw new Exception("response errror");
                }
                int lengthOfFile = urlConnection.getContentLength();
                Log.i("ertewu", "length of file is:" + lengthOfFile);

                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(mSkinFile);
                byte[] buffer = new byte[1024];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

            } catch (Exception e) {

            }
            return null;

            // URLConnection conexion = url.openConnection();
            // conexion.connect();

            // int lenghtOfFile = conexion.getContentLength();
            // Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);
            //
            // InputStream input = new BufferedInputStream(url.openStream());
            // OutputStream output = new FileOutputStream(
            // "/sdcard/some_photo_from_gdansk_poland.jpg");
            //
            // byte data[] = new byte[1024];
            //
            // long total = 0;
            //
            // while ((count = input.read(data)) != -1) {
            // total += count;
            // publishProgress("" + (int) ((total * 100) / lenghtOfFile));
            // output.write(data, 0, count);
            // }
            //
            // output.flush();
            // output.close();
            // input.close();
            // return null;

        }

        @Override
        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * http://stackoverflow.com/questions/5079868/create-a-ninepatch-
         * ninepatchdrawable-in-runtime <br>
         * 难到我server端准备得不够对？ 分为source与compiled 9patch bitmap，大概我所用得是前者..
         */
        @Override
        protected void onPostExecute(String unused) {
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
            Bitmap bitmap = BitmapFactory.decodeFile(mSkinFile);
            byte[] chunk = bitmap.getNinePatchChunk();
            boolean result = NinePatch.isNinePatchChunk(chunk);

            Log.i("ertewu", "result is:" + result);
            if (result) {
                NinePatchDrawable patchy = new NinePatchDrawable(bitmap, chunk,
                        new Rect(), null);

                View rootLayout = findViewById(R.id.root_layout);
                // rootLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
                rootLayout.setBackgroundDrawable(patchy);
                startBtn.setVisibility(View.GONE);
            }
        }
    }
}