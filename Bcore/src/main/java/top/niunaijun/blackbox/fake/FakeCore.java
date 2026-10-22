package top.niunaijun.blackbox.fake;

import android.app.ActivityThread;
import top.niunaijun.jnihook.ReflectCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/FakeCore.class */
public class FakeCore {
    public static void init() {
        ReflectCore.set(ActivityThread.class);
    }
}
