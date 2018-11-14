#include <jni.h>

#define JNIREG_CLASS   "zystudio/nativemodule/CaseNativeInvoke"
#define JNIPAR_CLASS   "zystudio/nativemodule/Person"


// jfieldID结构体，用于保存"Person.java的fieldID"
struct PersonOffsets {
    jfieldID name;
    jfieldID age;
    jfieldID height;
} gPersonOffsets;


typedef struct tagPerson {
    char mName[10];
    int mAge;
    float mHeight;
} Person;

static Person gPersons[] = {
        {"skywang", 25, 175},
        {"eman",    30, 166},
        {"Dan",     51, 172},
};


#define  NELEM(x) ((int) (sizeof(x)/sizeof((x)[0])))
#define GPERSON_NUM NELEM(gPersons)

int register_person_info_func(JNIEnv *env) ;
