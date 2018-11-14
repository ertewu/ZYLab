#include "person_info.h"
#include "native-lib.h"

//相当于是native的数据，填充到一个传进来的 java class 对象里边了
JNIEXPORT jint JNICALL getPersonInfoByIndex(JNIEnv *env, jobject clazz, jobject person, jint index) {
    if ((int) index < 0 || (int) index >= GPERSON_NUM)
        return -1;

    Person *pPerson = &gPersons[index];
    jstring name = (*env)->NewStringUTF(env, pPerson->mName); // char* to jstring

    (*env)->SetObjectField(env, person, gPersonOffsets.name, name);

    (*env)->SetIntField(env, person, gPersonOffsets.age, pPerson->mAge);

    (*env)->SetFloatField(env, person, gPersonOffsets.height, pPerson->mHeight);

    LOGD("%s index-%d  mName:%s , mAge:%d , mHeight:%f\n", __func__, index, pPerson->mName, pPerson->mAge, pPerson->mHeight);

    return 0;
}


static void nativeClassInit(JNIEnv *env) {

    jclass personClass = (*env)->FindClass(env, JNIPAR_CLASS);

    //注意String后边的分号，缺了这个不行..
    gPersonOffsets.name = (*env)->GetFieldID(env, personClass, "mName", "Ljava/lang/String;");
    gPersonOffsets.age = (*env)->GetFieldID(env, personClass, "mAge", "I");
    gPersonOffsets.height = (*env)->GetFieldID(env, personClass, "mHeight", "F");
}

static JNINativeMethod method_table[] = {
        {"getPersonInfoByIndex", "(Lzystudio/nativemodule/Person;I)I", (void *) getPersonInfoByIndex}
};

static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;
    clazz = (*env)->FindClass(env, className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }

    if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

int register_person_info_func(JNIEnv *env) {
    nativeClassInit(env);
    return registerNativeMethods(env, JNIREG_CLASS, method_table, NELEM(method_table));
}

