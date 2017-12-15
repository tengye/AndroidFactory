//
// Created by teng on 17/12/15.
//
#include "com_teng_androidfactory_JniTest.h"

JNIEXPORT jstring JNICALL Java_com_teng_androidfactory_JniTest_getJniString
        (JNIEnv *env, jobject ojb)
{
    return (*env) -> NewStringUTF(env,"Hello, I'm from jni");
}

