package com.example.demo.cases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.example.demo.R;


public class CaseForUri {

    private final static  String PACKAGE_NAME="com.example.demo";
    private static CaseForUri sCase;

    public static CaseForUri obtain() {
        if (sCase == null) {
            sCase = new CaseForUri();
        }
        return sCase;
    }

    public void work(Activity activity) {
//        showResourceUriDemo(activity);
        showFileImageUriDemo(activity);
    }

    private void showResourceUriDemo(Activity activity) {

        // 这个管用
        String uriStr = "android.resource://" + PACKAGE_NAME + "/"
                + R.drawable.motor;
        // 这个也管用
        // String uriStr = "android.resource://" + PACKAGE_NAME +
        // "/drawable/motor";

        Uri uri = Uri.parse(uriStr);
        // 通过log打出,authority是com.example.demo，scheme是android.resource
        // 而toString 就是我拼的那个字符串
        Log.i("ertewu", "r35:" + uri.getAuthority() + "|" + uri.getScheme()
                + "|" + uri.toString());
        ImageView view = new ImageView(activity);
        view.setImageURI(uri);
        new AlertDialog.Builder(activity).setView(view).create().show();
    }

    private void showFileImageUriDemo(Activity activity){
        exportDrawableToFile(activity);
        String dirPath=activity.getDir("image", Context.MODE_PRIVATE).getAbsolutePath();
        String filePath=dirPath+"/checkon";
        File file=new File(filePath);
        Uri uri=Uri.fromFile(file);
        //authority竟然是空
        Log.i("ertewu", "r60:"+uri.getAuthority()+"————|"+uri.getScheme()+"|"+uri.toString());
        try {
            Thread.currentThread().sleep(2000);
            ImageView view = new ImageView(activity);
            view.setImageURI(uri);
            new AlertDialog.Builder(activity).setView(view).create().show();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void exportDrawableToFile(Context ctx) {
        Bitmap bitmap=BitmapFactory.decodeResource(ctx.getResources(), R.drawable.checkon);

        String dirPath=ctx.getDir("image", Context.MODE_PRIVATE).getAbsolutePath();
        String filePath=dirPath+"/checkon";
        File outFile=new File(filePath);
        try {
            FileOutputStream outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
