LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := native-lib


# LOCAL_SRC_FILES :=src/main/cpp/native-lib.cpp  src/main/cpp/pthread-demo1.cpp src/main/cpp/pthread-demo2.cpp
# LOCAL_C_INCLUDES :=src/main/cpp/native-lib.h src/main/cpp/pthread_demo1.h src/main/cpp/pthread_demo2.h
# LOCAL_SRC_FILES := $(LOCAL_PATH)/../cpp/native-lib.cpp  $(LOCAL_PATH)/../cpp/pthread-demo1.cpp $(LOCAL_PATH)/../cpp/pthread-demo2.cpp


LOCAL_SRC_FILES := native-lib.cpp  pthread_demo1.cpp pthread_demo2.cpp
LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)
