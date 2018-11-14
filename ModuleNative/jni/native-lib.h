//
// Created by zylab on 2017/12/17.
//

#ifndef ZYLAB_NATIVE_LIB_H
#define ZYLAB_NATIVE_LIB_H

#include <jni.h>
#include <android/log.h>

#ifdef  __cplusplus
extern "C" {
#endif

#define  LOG_TAG "ZYStudio"

/**
 *
 *  #define LOGI(msg)  __android_log_write(ANDROID_LOG_INFO, LOG_TAG, msg)  底下那个宏跟上边那个是重合的
 */

#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG, __VA_ARGS__)

#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG, __VA_ARGS__)

#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG, __VA_ARGS__)


//extern static JavaVM* jvm;
static JavaVM *jvm;

#ifdef  __cplusplus
};
#endif

#endif //ZYLAB_NATIVE_LIB_H
