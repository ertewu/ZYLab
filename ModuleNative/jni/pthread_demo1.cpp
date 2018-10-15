//
// Created by zylab on 2017/12/16.
//
#include <pthread.h>
#include <jni.h>
#include <android/log.h>
#include "pthread_demo1.h"


//加了这一句，就找到了函数,现在这个，也可以了

void *run1(void *args) {
    __android_log_write(ANDROID_LOG_INFO, "ZYStudio", "pThread Demo1 invoke run1 occured");
    return NULL;
}

JNIEXPORT jint JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_nativeThread(JNIEnv *env, jobject thiz) {
    __android_log_write(ANDROID_LOG_INFO, "ZYStudio", "pThread Demo1 invoke 2 occured");
    pthread_t thread1;
    int a = pthread_create(&thread1, NULL, run1, NULL);
    return a;
}




