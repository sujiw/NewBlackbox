//
// Created by Milk on 2021/5/5.
//

//
// Created by Milk on 5/5/21.
//

#include <cstring>
#include <android/api-level.h>
#include "VMClassLoaderHook.h"
#import "JniHook/JniHook.h"
static bool hideXposedClass = false;

HOOK_JNI(jobject, findLoadedClass, JNIEnv *env, jobject obj, jobject class_loader, jstring name) {
    const char * nameC = env->GetStringUTFChars(name, JNI_FALSE);
//     ALOGD("findLoadedClass: %s", nameC);
    if (hideXposedClass) {
        if (strstr(nameC, "de/robv/android/xposed/") ||
            strstr(nameC, "me/weishu/epic") ||
            strstr(nameC, "me/weishu/exposed") ||
            strstr(nameC, "de.robv.android") ||
            strstr(nameC, "me.weishu.epic") ||
            strstr(nameC, "me.weishu.exposed")) {
            env->ReleaseStringUTFChars(name, nameC);
            return nullptr;
        }
    }
    jobject result = orig_findLoadedClass(env, obj, class_loader, name);
    env->ReleaseStringUTFChars(name, nameC);
    return result;
}

void VMClassLoaderHook::init(JNIEnv *env) {
    // Android 14+ 的 VMClassLoader.findLoadedClass 不再是 JNI native 方法，
    // JniHook 读到的原函数指针为空，注册后任何类加载都会跳 0 地址；
    // Xposed 隐藏改由 hideXposed 的 maps 清理负责。
    if (android_get_device_api_level() >= 34) {
        ALOGD("VMClassLoaderHook: skip findLoadedClass hook on api %d",
              android_get_device_api_level());
        return;
    }
    const char *className = "java/lang/VMClassLoader";
    JniHook::HookJniFun(env, className, "findLoadedClass", "(Ljava/lang/ClassLoader;Ljava/lang/String;)Ljava/lang/Class;",
                        (void *) new_findLoadedClass,
                        (void **) (&orig_findLoadedClass), true);
}

void VMClassLoaderHook::hideXposed() {
    hideXposedClass = true;
}
