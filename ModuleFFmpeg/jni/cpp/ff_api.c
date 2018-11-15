#include <stdio.h>
#include <libavutil/log.h>
#include <libavutil/pixdesc.h>
#include <libavformat/avformat.h>
//#include "FFmpegB/libavutil/log.h"
#include "jni_helper.h"
#include "ff_api.h"


int ffmpeg_init( ){
  av_register_all();
  avformat_network_init();
  printf("will exit\n");
  return 0;
}

int ff_dump_stream_info(FFVideoInfo *jInfo , const char * url) {

    AVFormatContext *ic = avformat_alloc_context();

    if (avformat_open_input(&ic, url, NULL, NULL) < 0) {
        LOGD("could not open source %s\n", url);
        return -1;
    }

    if (avformat_find_stream_info(ic, NULL) < 0) {
        LOGD("could not find stream information\n");
        return -1;
    }

    LOGD("---------- dumping stream info ----------");

    LOGD("input format: %s\n", ic->iformat->name);
    LOGD("nb_streams: %d\n", ic->nb_streams);

    int64_t start_time = ic->start_time / AV_TIME_BASE;
    LOGD("start_time: %lld\n", start_time);

    int64_t duration = ic->duration / AV_TIME_BASE;
    LOGD("duration: %lld s\n", duration);

    int video_stream_idx = av_find_best_stream(ic, AVMEDIA_TYPE_VIDEO, -1, -1, NULL, 0);
    if (video_stream_idx >= 0) {
        AVStream *video_stream = ic->streams[video_stream_idx];
        LOGD("video nb_frames: %lld\n", video_stream->nb_frames);
        LOGD("video codec_id: %d\n", video_stream->codec->codec_id);
        LOGD("video codec_name: %s\n", avcodec_get_name(video_stream->codec->codec_id));
        LOGD("video width x height: %d x %d\n", video_stream->codec->width, video_stream->codec->height);
        LOGD("video pix_fmt: %d\n", video_stream->codec->pix_fmt);

        char * fmt_name=av_get_pix_fmt_name(video_stream->codec->pix_fmt);
        LOGD("video pix_fmt name: %s\n", fmt_name);

        LOGD("video bitrate %lld kb/s\n", (int64_t) video_stream->codec->bit_rate / 1000);
        LOGD("video avg_frame_rate: %d fps\n", video_stream->avg_frame_rate.num/video_stream->avg_frame_rate.den);

        LOGD("JInfo assign begin");

//        FFVideoInfo info= *jInfo;  info.nbFrames=video_stream->nb_frames ;  这样写不行，还有问题
        jInfo->nbFrames=video_stream->nb_frames;
        jInfo->pix_fmt = video_stream->codec->pix_fmt;
        jInfo->width = video_stream->codec->width;
        jInfo->height = video_stream->codec->height;
        jInfo->avg_frame_rate = video_stream->avg_frame_rate.num / video_stream->avg_frame_rate.den;
        jInfo->bitrate=video_stream->codec->bit_rate;
        jInfo->pix_fmt_name=fmt_name;
        LOGD("JInfo assign end");
    }

    int audio_stream_idx = av_find_best_stream(ic, AVMEDIA_TYPE_AUDIO, -1, -1, NULL, 0);
    if (audio_stream_idx >= 0) {
        AVStream *audio_stream = ic->streams[audio_stream_idx];
        LOGD("audio codec_id: %d\n", audio_stream->codec->codec_id);
        LOGD("audio codec_name: %s\n", avcodec_get_name(audio_stream->codec->codec_id));
        LOGD("audio sample_rate: %d\n", audio_stream->codec->sample_rate);
        LOGD("audio channels: %d\n", audio_stream->codec->channels);
        LOGD("audio sample_fmt: %d\n", audio_stream->codec->sample_fmt);
        LOGD("audio frame_size: %d\n", audio_stream->codec->frame_size);
        LOGD("audio nb_frames: %lld\n", audio_stream->nb_frames);
        LOGD("audio bitrate %lld kb/s\n", (int64_t) audio_stream->codec->bit_rate / 1000);
    }

    LOGD("---------- dumping stream info ----------");

    avformat_close_input(&ic);

    return 0;
}

