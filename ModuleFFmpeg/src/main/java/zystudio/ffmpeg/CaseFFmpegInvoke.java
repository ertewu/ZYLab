package zystudio.ffmpeg;

public class CaseFFmpegInvoke {

//    private static final String URL="https://bizcommon.alicdn.com/KM596pxVraOsnEmgAP7/9RRTJjqkg6FyoQIDu1f%40%40hd.mp4";

    //http是可以的，上边的Https 就不太行，估计是没加ssl..
//    private static final String URL="https://bizcommon.alicdn.com/KM596pxVraOsnEmgAP7/9RRTJjqkg6FyoQIDu1f%40%40hd.mp4";


    // 本地url 是可以的，上边那个就不太行
    private static final String URL="/sdcard/0_MyDir/vid.mp4";

    private FFmpegUtil mUtil;

    public CaseFFmpegInvoke(){
        mUtil=new FFmpegUtil();
    }

    public void work() {
      mUtil.getMediaInfo(URL);
    }
}
