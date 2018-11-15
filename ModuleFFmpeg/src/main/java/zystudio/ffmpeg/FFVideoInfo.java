package zystudio.ffmpeg;

public class FFVideoInfo {
    long  nbFrames; //帧数
    int codecId;  //codecId
    int codecName; //codecName
    int width;
    int height;
    int pix_fmt; //video_stream_codec->pix_fmt
    long bitrate; //bitrate
    int avg_frame_rate; //帧数

    @Override
    public String toString() {
        return "nbFrame:"+nbFrames+"|width:"+width+"|height:"+height+"|bitrate:"+bitrate+"|avg_frame_rate:"+avg_frame_rate;
    }
}
