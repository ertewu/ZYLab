# Android.mk for FFmpeg

LOCAL_PATH := $(call my-dir)
MY_FFMPEG_LIB := $(LOCAL_PATH)/../jniLibs/armeabi

# FFmpeg library , CLEAR_VARS 清理很多变量，但不清理LOCAL_PATH, 但我上边的FFMPEG_LIB 是会被清理的,要按照Android的文档，改为MY_FFMPEG_LIB,也就是加MY
include $(CLEAR_VARS)
LOCAL_MODULE := avcodec
LOCAL_SRC_FILES := $(MY_FFMPEG_LIB)/libavcodec-56.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avdevice
LOCAL_SRC_FILES :=  $(MY_FFMPEG_LIB)/libavdevice-56.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avfilter
LOCAL_SRC_FILES := $(MY_FFMPEG_LIB)/libavfilter-5.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avformat
LOCAL_SRC_FILES := $(MY_FFMPEG_LIB)/libavformat-56.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avutil
LOCAL_SRC_FILES := $(MY_FFMPEG_LIB)/libavutil-54.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := postproc
LOCAL_SRC_FILES :=  $(MY_FFMPEG_LIB)/libpostproc-53.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := swresample
LOCAL_SRC_FILES := $(MY_FFMPEG_LIB)/libswresample-1.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := swscale
LOCAL_SRC_FILES := $(MY_FFMPEG_LIB)/libswscale-3.so
include $(PREBUILT_SHARED_LIBRARY)

# Program
include $(CLEAR_VARS)
LOCAL_MODULE := ffmpeg_api
LOCAL_C_INCLUDES += $(LOCAL_PATH)/include cpp/ff_api.h  cpp/jni_helper.h
LOCAL_SRC_FILES := cpp/ff_api.c  cpp/jni_helper.cpp
LOCAL_LDLIBS := -llog -lz
LOCAL_SHARED_LIBRARIES := avcodec avdevice avfilter avformat avutil postproc swresample swscale
include $(BUILD_SHARED_LIBRARY)



