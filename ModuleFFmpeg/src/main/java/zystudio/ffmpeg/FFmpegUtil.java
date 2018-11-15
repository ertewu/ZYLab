package zystudio.ffmpeg;

import android.util.Log;

public class FFmpegUtil {

    static {
        System.loadLibrary("ffmpeg_api");
    }

    public native int getVideoInfo(FFVideoInfo info, String url);

    public void getMediaInfo(String url) {
        Log.i("ZYStudio", "getMediaInfo start:" + url);
        FFVideoInfo info = new FFVideoInfo();
        getVideoInfo(info, url);
        Log.i("ZYStudio", "getMediaInfo end info:" + info.toString());
    }

}
