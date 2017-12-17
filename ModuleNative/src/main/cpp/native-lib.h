//
// Created by zylab on 2017/12/17.
//

#ifndef ZYLAB_NATIVE_LIB_H
#define ZYLAB_NATIVE_LIB_H

#include <jni.h>

#ifdef  __cplusplus
extern "C" {
#endif

#define  LOG_TAG "ZYStudio"
#define LOGI(a)  __android_log_write(ANDROID_LOG_INFO, LOG_TAG, a)


//extern static JavaVM* jvm;
static JavaVM* jvm;

#ifdef  __cplusplus
};
#endif

#endif //ZYLAB_NATIVE_LIB_H
