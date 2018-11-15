#ifndef TPP_FF_API
#define TPP_FF_API

#include <jni.h>

#define  FFVIDEO_INFO_CLS  "zystudio/ffmpeg/FFVideoInfo"

#define FF_LOGD(format, ...) av_log(NULL, AV_LOG_INFO, format, ##__VA_ARGS__);

/**
 * 用来给上层传递视频信息的数据结构
 */
typedef struct tagVideoInfo{

  long  nbFrames; //帧数
  int codecId;  //codecId
  int codecName; //codecName
  int width;
  int height;
  int pix_fmt; //video_stream_codec->pix_fmt
  long bitrate; //bitrate
  int avg_frame_rate; //帧数
} FFVideoInfo;


int ffmpeg_init();

int ff_dump_stream_info(FFVideoInfo *info , const char * url);

#endif //TPP_FF_API

