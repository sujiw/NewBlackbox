package black.android.providers;

import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BStaticField;

/* JADX INFO: loaded from: classes.jar:black/android/providers/Settings.class */
@BClassName("android.provider.Settings")
public interface Settings {

    /* JADX INFO: loaded from: classes.jar:black/android/providers/Settings$ContentProviderHolder.class */
    @BClassName("android.provider.Settings$ContentProviderHolder")
    public interface ContentProviderHolder {
        @BField
        IInterface mContentProvider();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/providers/Settings$Global.class */
    @BClassName("android.provider.Settings$Global")
    public interface Global {
        @BStaticField
        Object sNameValueCache();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/providers/Settings$NameValueCache.class */
    @BClassName("android.provider.Settings$NameValueCache")
    public interface NameValueCache {
        @BField
        Object mContentProvider();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/providers/Settings$NameValueCacheOreo.class */
    @BClassName("android.provider.Settings$NameValueCache")
    public interface NameValueCacheOreo {
        @BField
        Object mProviderHolder();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/providers/Settings$Secure.class */
    @BClassName("android.provider.Settings$Secure")
    public interface Secure {
        @BStaticField
        Object sNameValueCache();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/providers/Settings$System.class */
    @BClassName("android.provider.Settings$System")
    public interface System {
        @BStaticField
        Object sNameValueCache();
    }
}
