//
// Created by alanchen on 15/9/2.
//

#include "yifeiyuan_practice_practicedemos_jni_HelloJni.h"
JNIEXPORT jstring JNICALL Java_yifeiyuan_practice_practicedemos_jni_HelloJni_helloJni
        (JNIEnv* env, jclass){

    jstring str = env->NewStringUTF("Hello World ! I am from Jni,Where do you come from?");
    return str;
}


