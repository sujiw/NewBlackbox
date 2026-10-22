package top.niunaijun.blackbox.fake.delegate;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IInterface;
import android.util.ArrayMap;
import black.android.app.BRActivityThread;
import black.android.app.BRActivityThreadProviderClientRecordP;
import black.android.app.BRIActivityManagerContentProviderHolder;
import black.android.content.BRContentProviderHolderOreo;
import black.android.providers.BRSettingsContentProviderHolder;
import black.android.providers.BRSettingsGlobal;
import black.android.providers.BRSettingsNameValueCache;
import black.android.providers.BRSettingsNameValueCacheOreo;
import black.android.providers.BRSettingsSecure;
import black.android.providers.BRSettingsSystem;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.fake.service.context.providers.ContentProviderStub;
import top.niunaijun.blackbox.fake.service.context.providers.SystemProviderStub;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/delegate/ContentProviderDelegate.class */
public class ContentProviderDelegate {
    public static final String TAG = "ContentProviderDelegate";
    private static Set<String> sInjected = new HashSet();

    public static void update(Object holder, String auth) {
        IInterface iInterface;
        IInterface bContentProvider;
        if (BuildCompat.isOreo()) {
            iInterface = BRContentProviderHolderOreo.get(holder).provider();
        } else {
            iInterface = BRIActivityManagerContentProviderHolder.get(holder).provider();
        }
        if (iInterface instanceof Proxy) {
            return;
        }
        switch (auth) {
            case "media":
            case "telephony":
            case "settings":
                bContentProvider = new SystemProviderStub().wrapper(iInterface, BlackBoxCore.getHostPkg());
                break;
            default:
                bContentProvider = new ContentProviderStub().wrapper(iInterface, BlackBoxCore.getHostPkg());
                break;
        }
        if (BuildCompat.isOreo()) {
            BRContentProviderHolderOreo.get(holder)._set_provider(bContentProvider);
        } else {
            BRIActivityManagerContentProviderHolder.get(holder)._set_provider(bContentProvider);
        }
    }

    public static void init() {
        clearSettingProvider();
        BlackBoxCore.getContext().getContentResolver().call(Uri.parse("content://settings"), "", (String) null, (Bundle) null);
        Object activityThread = BlackBoxCore.mainThread();
        ArrayMap<Object, Object> map = (ArrayMap) BRActivityThread.get(activityThread).mProviderMap();
        for (Object value : map.values()) {
            String[] mNames = BRActivityThreadProviderClientRecordP.get(value).mNames();
            if (mNames != null && mNames.length > 0) {
                String providerName = mNames[0];
                if (!sInjected.contains(providerName)) {
                    sInjected.add(providerName);
                    IInterface iInterface = BRActivityThreadProviderClientRecordP.get(value).mProvider();
                    BRActivityThreadProviderClientRecordP.get(value)._set_mProvider(new ContentProviderStub().wrapper(iInterface, BlackBoxCore.getHostPkg()));
                    BRActivityThreadProviderClientRecordP.get(value)._set_mNames(new String[]{providerName});
                }
            }
        }
    }

    public static void clearSettingProvider() {
        Object cache;
        Object cache2 = BRSettingsSystem.get().sNameValueCache();
        if (cache2 != null) {
            clearContentProvider(cache2);
        }
        Object cache3 = BRSettingsSecure.get().sNameValueCache();
        if (cache3 != null) {
            clearContentProvider(cache3);
        }
        if (Build.VERSION.SDK_INT >= 17 && BRSettingsGlobal.getRealClass() != null && (cache = BRSettingsGlobal.get().sNameValueCache()) != null) {
            clearContentProvider(cache);
        }
    }

    private static void clearContentProvider(Object cache) {
        if (BuildCompat.isOreo()) {
            Object holder = BRSettingsNameValueCacheOreo.get(cache).mProviderHolder();
            if (holder != null) {
                BRSettingsContentProviderHolder.get(holder)._set_mContentProvider(null);
                return;
            }
            return;
        }
        BRSettingsNameValueCache.get(cache)._set_mContentProvider(null);
    }
}
