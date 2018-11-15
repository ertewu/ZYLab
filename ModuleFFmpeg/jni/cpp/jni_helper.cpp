#include "jni_helper.h"

extern "C" {
   #include "ff_api.h"
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm,void *reserved){
    jvm=vm;
    JNIEnv *env;
    jint result =-1;
    if(vm->GetEnv(reinterpret_cast<void**> (&env),JNI_VERSION_1_6)!=JNI_OK){
        return result;
    }
    registerNativeMethods(env, JNIREG_CLASS, method_table , NUM_METHOD(method_table));
    return JNI_VERSION_1_6;
}


static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;
//    clazz = (*env)->FindClass(env, className);  这是c格式的
    clazz = env->FindClass(className); //这是c++格式的，在这个文件里，得用这个
    if (clazz == NULL) {
        return JNI_FALSE;
    }

    if (*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

JNIEXPORT jint JNICALL getVideoInfoByFFmpeg(JNIEnv* env, jobject jVideoInfo, jstring url) {

     jint result = -1;

     FFVideoInfo ffInfo;

     initJClsField(env);

     ffmpeg_init();

     //* or & ; jstring to char*
     result=ff_dump_stream_info(ffInfo ,url);

     setJVideoInfo(env, jVideoInfo, ffInfo);

     return result;
}


void setJVideoInfo(JNIEnv* env, jobject jVideoInfo , FFVideoInfo ffInfo) {
 (*env)->SetLongField(env,jVideoInfo, jVideoInfoFields.nbFrames, ffInfo.nbFrames);
 //先找几个有用的字段得了
 (*env)->SetIntField(env,jVideoInfo, jVideoInfoFields.width, ffInfo.width);
 (*env)->SetIntField(env,jVideoInfo, jVideoInfoFields.height, ffInfo.height);
 (*env)->SetLongField(env,jVideoInfo, jVideoInfoFields.bitrate, ffInfo.bitrate);
 //这个估计要转成string的才可以
 (*env)->SetIntField(env,jVideoInfo, jVideoInfoFields.pix_fmt, ffInfo.pix_fmt);
 (*env)->SetIntField(env,jVideoInfo, jVideoInfoFields.avg_frame_rate, ffInfo.avg_frame_rate);
}


int initJClsField(JNIEnv *env){

    jclass jFFVideoInfoCls=(*env)->FindClass(env, FFVIDEO_INFO_CLS);

    jVideoInfoFields.nbFrames= (*env)->GetFieldID(env ,jFFVideoInfoCls,"nbFrames","L");
    jVideoInfoFields.codecId= (*env)->GetFieldID(env, jFFVideoInfoCls,"codecId","I");
    jVideoInfoFields.codecName= (*env)->GetFieldID(env, jFFVideoInfoCls, "codecName","I");
    jVideoInfoFields.width=(*env)->GetFieldID(env, jFFVideoInfoCls, "width","I");
    jVideoInfoFields.height=(*env)->GetFieldID(env, jFFVideoInfoCls,"height","I");
    jVideoInfoFields.pix_fmt=(*env)->GetFieldID(env, jFFVideoInfoCls,"pix_fmt","I");
    jVideoInfoFields.bitrate=(*env)->GetFieldID(env, jFFVideoInfoCls,"bitrate","L");
    jVideoInfoFields.avg_frame_rate=(*env)->GetFieldID(env, jFFVideoInfoCls,"avg_frame_rate","I");
}

