package top.niunaijun.blackbox.utils;

import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/CloseUtils.class */
public class CloseUtils {
    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
