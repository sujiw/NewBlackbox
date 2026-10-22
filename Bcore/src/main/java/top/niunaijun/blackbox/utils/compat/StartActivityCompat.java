package top.niunaijun.blackbox.utils.compat;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/StartActivityCompat.class */
public class StartActivityCompat {
    private static int index;
    private static int appThreadIndex;
    private static int callingPageIndex;
    private static int callingFeatureIdIndex;
    private static int intentIndex;
    private static int resolvedTypeIndex;
    private static int resultToIndex;
    private static int resultWhoIndex;
    private static int requestCodeIndex;
    private static int flagsIndex;
    private static int profilerInfoIndex;
    private static int optionsIndex;

    static {
        index = 0;
        if (BuildCompat.isR()) {
            int i = index;
            index = i + 1;
            appThreadIndex = i;
            int i2 = index;
            index = i2 + 1;
            callingPageIndex = i2;
            int i3 = index;
            index = i3 + 1;
            callingFeatureIdIndex = i3;
            int i4 = index;
            index = i4 + 1;
            intentIndex = i4;
            int i5 = index;
            index = i5 + 1;
            resolvedTypeIndex = i5;
            int i6 = index;
            index = i6 + 1;
            resultToIndex = i6;
            int i7 = index;
            index = i7 + 1;
            resultWhoIndex = i7;
            int i8 = index;
            index = i8 + 1;
            requestCodeIndex = i8;
            int i9 = index;
            index = i9 + 1;
            flagsIndex = i9;
            int i10 = index;
            index = i10 + 1;
            profilerInfoIndex = i10;
            int i11 = index;
            index = i11 + 1;
            optionsIndex = i11;
        } else {
            int i12 = index;
        index = i12 + 1;
        appThreadIndex = i12;
        int i13 = index;
        index = i13 + 1;
        callingPageIndex = i13;
        int i14 = index;
        index = i14 + 1;
        intentIndex = i14;
        int i15 = index;
        index = i15 + 1;
        resolvedTypeIndex = i15;
        int i16 = index;
        index = i16 + 1;
        resultToIndex = i16;
        int i17 = index;
        index = i17 + 1;
        resultWhoIndex = i17;
        int i18 = index;
        index = i18 + 1;
        requestCodeIndex = i18;
        int i19 = index;
        index = i19 + 1;
        flagsIndex = i19;
        int i20 = index;
        index = i20 + 1;
        profilerInfoIndex = i20;
        int i21 = index;
        index = i21 + 1;
        optionsIndex = i21;
        }
    }

    public static Object getIApplicationThread(Object[] args) {
        if (args == null || args.length < appThreadIndex) {
            return null;
        }
        return args[appThreadIndex];
    }

    public static String getCallingPackage(Object[] args) {
        if (args == null || args.length < callingPageIndex) {
            return null;
        }
        return (String) args[callingPageIndex];
    }

    public static Intent getIntent(Object[] args) {
        if (args == null || args.length < intentIndex) {
            return null;
        }
        return (Intent) args[intentIndex];
    }

    public static String getResolvedType(Object[] args) {
        if (args == null || args.length < resolvedTypeIndex) {
            return null;
        }
        return (String) args[resolvedTypeIndex];
    }

    public static IBinder getResultTo(Object[] args) {
        if (args == null || args.length < resultToIndex) {
            return null;
        }
        return (IBinder) args[resultToIndex];
    }

    public static String getResultWho(Object[] args) {
        if (args == null || args.length < resultWhoIndex) {
            return null;
        }
        return (String) args[resultWhoIndex];
    }

    public static int getRequestCode(Object[] args) {
        if (args == null || args.length < requestCodeIndex) {
            return -1;
        }
        return ((Integer) args[requestCodeIndex]).intValue();
    }

    public static int getFlags(Object[] args) {
        if (args == null || args.length < flagsIndex) {
            return -1;
        }
        return ((Integer) args[flagsIndex]).intValue();
    }

    public static Object getProfilerInfo(Object[] args) {
        if (args == null || args.length < profilerInfoIndex) {
            return null;
        }
        return args[profilerInfoIndex];
    }

    public static Bundle getOptions(Object[] args) {
        if (args == null || args.length < optionsIndex) {
            return null;
        }
        return (Bundle) args[optionsIndex];
    }

    public static int getAppThreadIndex() {
        return appThreadIndex;
    }

    public static void setAppThreadIndex(int appThreadIndex2) {
        appThreadIndex = appThreadIndex2;
    }

    public static int getCallingPageIndex() {
        return callingPageIndex;
    }

    public static void setCallingPageIndex(int callingPageIndex2) {
        callingPageIndex = callingPageIndex2;
    }

    public static int getIntentIndex() {
        return intentIndex;
    }

    public static void setIntentIndex(int intentIndex2) {
        intentIndex = intentIndex2;
    }

    public static int getResolvedTypeIndex() {
        return resolvedTypeIndex;
    }

    public static void setResolvedTypeIndex(int resolvedTypeIndex2) {
        resolvedTypeIndex = resolvedTypeIndex2;
    }

    public static int getResultToIndex() {
        return resultToIndex;
    }

    public static void setResultToIndex(int resultToIndex2) {
        resultToIndex = resultToIndex2;
    }

    public static int getResultWhoIndex() {
        return resultWhoIndex;
    }

    public static void setResultWhoIndex(int resultWhoIndex2) {
        resultWhoIndex = resultWhoIndex2;
    }

    public static int getRequestCodeIndex() {
        return requestCodeIndex;
    }

    public static void setRequestCodeIndex(int requestCodeIndex2) {
        requestCodeIndex = requestCodeIndex2;
    }

    public static int getFlagsIndex() {
        return flagsIndex;
    }

    public static void setFlagsIndex(int flagsIndex2) {
        flagsIndex = flagsIndex2;
    }

    public static int getProfilerInfoIndex() {
        return profilerInfoIndex;
    }

    public static void setProfilerInfoIndex(int profilerInfoIndex2) {
        profilerInfoIndex = profilerInfoIndex2;
    }

    public static int getOptionsIndex() {
        return optionsIndex;
    }

    public static void setOptionsIndex(int optionsIndex2) {
        optionsIndex = optionsIndex2;
    }
}
