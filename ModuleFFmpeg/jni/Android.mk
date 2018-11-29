# Android.mk for FFmpeg

LOCAL_PATH := $(call my-dir)
MY_FFMPEG_LIB := $(LOCAL_PATH)/../jniLibs/armeabi

# FFmpeg library , CLEAR_VARS 清理很多变量，但不清理LOCAL_PATH, 但我上边的FFMPEG_LIB 是会被清理的,要按照Android的文档，改为MY_FFMPEG_LIB,也就是加MY
include $(CLEAR_VARS)
LOCAL_MODULE :=tbffmpeg
LOCAL_SRC_FILES := $(MY_FFMPEG_LIB)/libtbffmpeg.so
include $(PREBUILT_SHARED_LIBRARY)


# Program
include $(CLEAR_VARS)
LOCAL_MODULE := ffmpeg_api
LOCAL_C_INCLUDES += $(LOCAL_PATH)/include cpp/ff_api.h  cpp/jni_helper.h
LOCAL_SRC_FILES := cpp/ff_api.c  cpp/jni_helper.cpp
LOCAL_LDLIBS := -llog -lz
LOCAL_SHARED_LIBRARIES := tbffmpeg
include $(BUILD_SHARED_LIBRARY)



