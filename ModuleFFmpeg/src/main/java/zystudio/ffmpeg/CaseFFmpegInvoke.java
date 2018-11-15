package zystudio.ffmpeg;

public class CaseFFmpegInvoke {

    private static final String URL="https://bizcommon.alicdn.com/KM596pxVraOsnEmgAP7/9RRTJjqkg6FyoQIDu1f%40%40hd.mp4";
    private FFmpegUtil mUtil;

    public CaseFFmpegInvoke(){
        mUtil=new FFmpegUtil();
    }

    public void work() {
      mUtil.getMediaInfo(URL);
    }
}
