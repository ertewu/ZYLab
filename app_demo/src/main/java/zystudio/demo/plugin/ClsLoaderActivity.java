package zystudio.demo.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import zystudio.mylib.utils.LogUtil;

/**
 * 用来解刨乐视视频的插件机制的
 * Created by leeco on 2017/7/29.
 */

public class ClsLoaderActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //乐视视频的clsLoader中的optimizedDirectory,就是这个参数
        String dir = getApplicationInfo().nativeLibraryDir;
        LogUtil.log("nativeLibraryDir:"+dir);
    }
}
