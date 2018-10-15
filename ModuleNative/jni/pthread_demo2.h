//
// Created by zylab on 2017/12/16.
// http://blog.csdn.net/jike0901xuye/article/details/50333195
//


#include <jni.h>
#include <unistd.h>
#include <pthread.h>

#ifndef ZYLAB_PTHREAD_DEMO2_H
#define ZYLAB_PTHREAD_DEMO2_H

#ifdef __cplusplus
 extern "C" {
#endif

JNIEXPORT void JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_init(JNIEnv *, jobject);

JNIEXPORT int JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_run(JNIEnv *, jobject, jobject);

JNIEXPORT int JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_recycle(JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif


#endif //ZYLAB_PTHREAD_DEMO2_H
