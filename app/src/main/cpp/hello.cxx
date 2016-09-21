#include <jni.h>
#include <android/log.h>

extern "C" jint
Java_diy_hellojni_MainActivity_getVersion(JNIEnv *env, jobject self) {
    int version = 0x000001ff;
    __android_log_print(ANDROID_LOG_DEBUG, "MainActivity", "version = %08X", version);
    return version;
}

