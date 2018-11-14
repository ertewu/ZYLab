//
// Created by zylab on 2017/12/15.
//
#include <string>
#include "native-lib.h"

extern "C" {
   //在c++ 中引用c的函数，头文件这样弄最方便了, 否则就得在person_info.h 中加 cplusplus宏的判断
   #include "person_info.h"
}

extern "C" JNIEXPORT jstring


/**
 * 实验 Native Code 打log
 */
JNICALL Java_zystudio_nativemodule_CaseNativeInvoke_stringFromJNI(JNIEnv *env, jobject) {
    std::string hello="Hello from C++";
    //这个是ok的
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio", "this is stringInJNI");
    //这个是ok的
    LOGI("this is stringInJNI2");
    //这个也是ok的
    __android_log_write(ANDROID_LOG_INFO,"ZYStudio", hello.c_str());
    return env->NewStringUTF(hello.c_str());
}


/**
 *  当Android的VM执行到C组件里的System.loadLibrary函数时，首先会去执行C组件里的JNI_OnLoad函数。 用途有二：
 *  一、告诉VM此C组件使用哪一个JNI版本,（如果*.so中没有实现JNI_OnLoad， 则VM默认该*.so使用最老的JNI1.1版本
 *  二、由于VM执行到 System.loadLibrary函数时，就会立即先呼叫JNI_OnLoad（），所以C组件的开发者可以籍由JNI_OnLoad()来进行C组件内的初期值设定（Initialization)
 *  三、如果一个so中有多个 JNI_OnLoad函数 ，貌似编译器会报错.. 反正在AndroidStudio里，cmake是报错了
 *  四、同时还有一个 JNI_OnUnLoad函数
 *  五、同一个so中，有多个JNI_OnLoad的话，编译器会报错,因为pthread_demo在用，这儿就先注释掉
 */

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm,void *reserved){
    LOGI("JNI_OnLoad occured");
    jvm=vm;//native-lib中的静态jvm变量
    JNIEnv *env;

    jint result =-1;

//     这个是JNI_1.4的写法，ijkplayer是用JNI_1.4的; fresco是用JNI_1.6的,但是我这样写编译不过，也不知道为啥
//    if ((*vm)->GetEnv(vm, (void**)&env, JNI_VERSION_1_4)!=JNI_OK ){
//        return result;
//    }
//    register_person_info_func(env) ;
//    return JNI_VERSION_1_4;


//    这个是JNI_1.6的写法，fresco的 init.cpp 是1.6的， 一定要加reinterpret_cast ，否则这里会编译不过
    if(vm->GetEnv(reinterpret_cast<void**> (&env),JNI_VERSION_1_6)!=JNI_OK){
        return result;
    }
    register_person_info_func(env) ;
    return JNI_VERSION_1_6;
}



