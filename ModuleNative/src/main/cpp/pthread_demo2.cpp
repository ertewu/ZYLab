//
// Created by zylab on 2017/12/16.
// http://blog.csdn.net/jike0901xuye/article/details/50333195   这个demo我是跑了起来的,起码nativework实是打了出来的
// Good 完全跑通了
//
#include "pthread_demo2.h"
#include <jni.h>
#include <sys/types.h>
#include <pthread.h>
#include <android/log.h>


static JavaVM *jvm=NULL;
static jobject jobj=NULL;
static jmethodID mid=NULL;

static int flag=-1;

static pthread_mutex_t mutex=PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t cond=PTHREAD_COND_INITIALIZER;

jint JNI_OnLoad(JavaVM *vm , void *reserved){
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio","JNI_OnLoad occured");
    jvm=vm;
    return JNI_VERSION_1_6;
}

void JNI_OnUnLoad(JavaVM *vm ,void *reserved){
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio","JNI_OnUnLoad occured");
    jvm=NULL;
}

//pthread中执行的函数
void *nativeWork(void *args){
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio","nativeWork occured");
    JNIEnv *env=NULL;
    if(0== jvm->AttachCurrentThread(&env,NULL)){
        while(flag==0){
            if(jobj==NULL){
                pthread_cond_wait(&cond,&mutex);
            }else {
                env->CallVoidMethod(jobj, mid);
                env->DeleteGlobalRef(jobj);
                jobj=NULL;
            }
        }
        jvm->DetachCurrentThread();
    }
    return (void *) 1;
}


JNIEXPORT void JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_init(JNIEnv *env, jobject){
   if(NULL==mid){
       jclass cls=env->FindClass("zystudio/nativemodule/LocalRunnable");
       mid=env->GetMethodID(cls,"run","()V");
       __android_log_write(ANDROID_LOG_INFO,"ZYStudio","init 1 occured");
       if(mid==NULL){
           return ;
       }
       __android_log_write(ANDROID_LOG_INFO,"ZYStudio","init 2 occured");
       flag=0;
       pthread_t thread;
       pthread_create(&thread, NULL ,nativeWork ,NULL);
   }
}

JNIEXPORT int JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_run(JNIEnv * env, jobject obj , jobject callback){
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio","run occured");
    flag=0;
    jobj=env->NewGlobalRef(callback);
    return pthread_cond_signal(&cond);
}

JNIEXPORT int JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_recycle(JNIEnv * env, jobject){
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio","recycle occured");
    flag=-1;
    env->DeleteGlobalRef(jobj);
}
