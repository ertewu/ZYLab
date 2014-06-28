package zystudio.demo.cases;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ImageView;

import zystudio.demo.R;


public class CaseForResourceUri {

    private final static  String PACKAGE_NAME="com.example.demo";
    private static CaseForResourceUri sCase;

    public static CaseForResourceUri obtain() {
        if (sCase == null) {
            sCase = new CaseForResourceUri();
        }
        return sCase;
    }

    public void work(Activity activity) {
        // showResourceUriDemo(activity);
        // showFileImageUriDemo(activity);
        decodeImageByFileUri(activity);
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

    private void decodeImageByFileUri(Activity act) {
        // 这个在drawable的，在这里不行，会有fileNotFound的exception
        // String uriStr = "android.resource://" + PACKAGE_NAME + "/"
        // + R.drawable.motor;
        // 用asset这样的也是出不出来file的，因为在apk打包后，其也不是file了
        // String uriStr = "file:///android_asset/motor_asset.jpg";
        // 这个还不行..
        // String uriStr = "android.resource://" + PACKAGE_NAME + "/"
        // + R.raw.motor_raw;
        // 这个还不行啊,raw res/raw res/drawable都用过了，不行啊
        String uriStr = "android.resource://" + PACKAGE_NAME + "/"
                + R.raw.motor_raw;
        Uri uri = Uri.parse(uriStr);
        try {
            ParcelFileDescriptor pfd = act.getContentResolver()
                    .openFileDescriptor(uri, "r");
            // AssetManager am = act.getAssets();
            // AssetFileDescriptor afd = am.openFd("motor_asset.jpg");
            FileDescriptor fd = pfd.getFileDescriptor();
            // FileDescriptor fd = am.getFileDescriptor();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap b = BitmapFactory.decodeFileDescriptor(fd, null, options);

            ImageView view = new ImageView(act);
            view.setImageBitmap(b);
            new AlertDialog.Builder(act).setView(view).create().show();
        } catch (FileNotFoundException e) {
            Log.i("ertewu", "exception:" + e.toString());
        }
    }

    private void showFileImageUriDemo(Activity activity){
//        exportDrawableToFile(activity);
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
