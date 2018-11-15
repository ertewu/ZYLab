/**
 * jni 接口用 c++ 做，因为扩展性好，ffmpeg是用c做的，但很多其它的库是c++做的
 */

#ifndef TPP_JNI_HELPER
#define TPP_JNI_HELPER

#include <jni.h>
#include <android/log.h>

#ifdef __cplusplus
extern "C" {
#endif

#include "ff_api.h"

#define LOG_TAG "ZYStudio"

#define JNIREG_CLASS "zystudio/ffmpeg/FFmpegUtil"

#define  NUM_METHOD(x) ((int) (sizeof(x)/sizeof((x)[0])))

#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG, __VA_ARGS__)

#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG, __VA_ARGS__)

#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG, __VA_ARGS__)

struct JFFVideoInfoFields {
    jfieldID nbFrames; //帧数
    jfieldID codecId;  //codecId
    jfieldID codecName; //codecName
    jfieldID width;
    jfieldID height;
    jfieldID pix_fmt; //video_stream_codec->pix_fmt
    jfieldID bitrate; //bitrate
    jfieldID avg_frame_rate; //帧数

} jVideoInfoFields;

static JavaVM *jvm;

//第一，第二个参数是对应native必须有的
JNIEXPORT jint JNICALL getVideoInfoByFFmpeg(JNIEnv *env, jobject clazz, jobject jVideoInfo, jstring url);

static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods, int numMethods);

int initJClsField(JNIEnv *env);

void setJVideoInfo(JNIEnv* env, jobject jVideoInfo , FFVideoInfo ffInfo);

//JNINativeMethod的第二个参数，很容易错的, 比如底下那个分号
static JNINativeMethod method_table[] = {
        {
                "getVideoInfo",
                "(Lzystudio/ffmpeg/FFVideoInfo;Ljava/lang/String;)I",
                (void *) getVideoInfoByFFmpeg
        }
};

#ifdef  __cplusplus
};
#endif
#endif //TPP_JNI_HELPER


