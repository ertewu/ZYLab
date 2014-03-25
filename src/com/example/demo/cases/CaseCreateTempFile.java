package com.example.demo.cases;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class CaseCreateTempFile {

    public static void showDemo(Context context) {
        String fileName = "newFileDemo";
        String externalPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        String absPath = externalPath + "/" + fileName;

        File file = new File(absPath);
        try {
            File tempFile1 = File.createTempFile("prefixone", "suffix");
            File tempFile2 = File.createTempFile("prefixtwo", "suffix",
                    Environment.getExternalStorageDirectory());
            Log.i("ertewu", "temp1:" + tempFile1.getAbsolutePath());
            Log.i("ertewu", "temp2:" + tempFile2.getAbsolutePath());
            // 实验得明temp文件在程序运行后，并没有自动删除，也不是会自动删除的
        } catch (IOException e) {
            Log.i("ertewu", e.toString());
            e.printStackTrace();
        }
    }
}
