package top.niunaijun.blackbox.utils.compat;

import dalvik.system.DexFile;
import java.util.ArrayList;
import java.util.List;
import top.niunaijun.blackbox.utils.Reflector;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/DexFileCompat.class */
public class DexFileCompat {
    public static List<Long> getCookies(ClassLoader classLoader) {
        List<Long> cookies = new ArrayList<>();
        List<DexFile> dexFiles = getDexFiles(classLoader);
        for (DexFile dexFile : dexFiles) {
            cookies.addAll(getCookies(dexFile));
        }
        return cookies;
    }

    public static List<Long> getCookies(DexFile dexFile) {
        List<Long> cookies = new ArrayList<>();
        if (dexFile == null) {
            return cookies;
        }
        try {
            Object object = Reflector.with(dexFile).field("mCookie").get();
            if (BuildCompat.isM()) {
                for (long l : (long[]) object) {
                    cookies.add(Long.valueOf(l));
                }
            } else {
                cookies.add(Long.valueOf(((Long) object).longValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookies;
    }

    private static List<DexFile> getDexFiles(ClassLoader classLoader) {
        List<DexFile> dexFiles = new ArrayList<>();
        Object[] dexElements = getDexElements(classLoader);
        for (Object dexElement : dexElements) {
            try {
                dexFiles.add((DexFile) Reflector.with(dexElement).field("dexFile").get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dexFiles;
    }

    private static Object[] getDexElements(ClassLoader classLoader) {
        Object dexPathList = getDexPathList(classLoader);
        if (dexPathList == null) {
            return new Object[0];
        }
        try {
            return (Object[]) Reflector.with(dexPathList).field("dexElements").get();
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0];
        }
    }

    private static Object getDexPathList(ClassLoader classLoader) {
        try {
            return Reflector.on("dalvik.system.BaseDexClassLoader").field("pathList").get(classLoader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
