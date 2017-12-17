//
// Created by zylab on 2017/12/17.
//

#ifndef ZYLAB_PTHREAD_DEMO1_H
#define ZYLAB_PTHREAD_DEMO1_H

#ifdef __cplusplus
extern "C" {
#endif
  void *run1(void *args);

JNIEXPORT jint JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_nativeThread(JNIEnv *env, jobject thiz) ;

#ifdef __cplusplus
}
#endif


#endif //ZYLAB_PTHREAD_DEMO1_H
