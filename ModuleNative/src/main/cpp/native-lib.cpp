//
// Created by zylab on 2017/12/15.
//
#include <jni.h>
#include <string>
#include <android/log.h>

extern "C"
JNIEXPORT jstring

#define  LOG_TAG "ZYStudio"
#define LOGI(a)  __android_log_write(ANDROID_LOG_INFO, LOG_TAG, a)

JNICALL
Java_zystudio_nativemodule_CaseNativeInvoke_stringFromJNI( JNIEnv *env, jobject) {
    std::string hello="Hello from C++";
    //这个是ok的
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio", "this is stringInJNI");
    //这个是ok的
    LOGI("this is stringInJNI2");
    //这个也是ok的
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio", hello.c_str());
    return env->NewStringUTF(hello.c_str());
}

