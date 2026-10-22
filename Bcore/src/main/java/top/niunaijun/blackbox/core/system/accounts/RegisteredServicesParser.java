package top.niunaijun.blackbox.core.system.accounts;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import top.niunaijun.blackbox.core.system.pm.PackageManagerCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/RegisteredServicesParser.class */
public class RegisteredServicesParser {
    public XmlResourceParser getParser(Context context, ServiceInfo serviceInfo, String name) {
        int xmlId;
        Bundle meta = serviceInfo.metaData;
        if (meta != null && (xmlId = meta.getInt(name)) != 0) {
            try {
                Resources resources = getResources(context, serviceInfo.applicationInfo);
                if (resources == null) {
                    return null;
                }
                return resources.getXml(xmlId);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public Resources getResources(Context context, ApplicationInfo appInfo) {
        return PackageManagerCompat.getResources(context, appInfo);
    }
}
