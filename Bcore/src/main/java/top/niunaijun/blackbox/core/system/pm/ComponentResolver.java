package top.niunaijun.blackbox.core.system.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/ComponentResolver.class */
public class ComponentResolver {
    public static final String TAG = "ComponentResolver";
    private final Object mLock = new Object();
    private final ActivityIntentResolver mActivities = new ActivityIntentResolver();
    private final ProviderIntentResolver mProviders = new ProviderIntentResolver();
    private final ActivityIntentResolver mReceivers = new ActivityIntentResolver();
    private final ServiceIntentResolver mServices = new ServiceIntentResolver();
    private final ArrayMap<String, BPackage.Provider> mProvidersByAuthority = new ArrayMap<>();

    void addAllComponents(BPackage pkg) {
        ArrayList<BPackage.ActivityIntentInfo> newIntents = new ArrayList<>();
        synchronized (this.mLock) {
            addActivitiesLocked(pkg, newIntents);
            addServicesLocked(pkg);
            addProvidersLocked(pkg);
            addReceiversLocked(pkg);
        }
    }

    void removeAllComponents(BPackage pkg) {
        synchronized (this.mLock) {
            removeAllComponentsLocked(pkg);
        }
    }

    private void removeAllComponentsLocked(BPackage pkg) {
        int componentSize = pkg.activities.size();
        for (int i = 0; i < componentSize; i++) {
            BPackage.Activity a = pkg.activities.get(i);
            this.mActivities.removeActivity(a, "activity");
        }
        int componentSize2 = pkg.providers.size();
        for (int i2 = 0; i2 < componentSize2; i2++) {
            BPackage.Provider p = pkg.providers.get(i2);
            this.mProviders.removeProvider(p);
            if (p.info.authority != null) {
                String[] names = p.info.authority.split(";");
                for (int j = 0; j < names.length; j++) {
                    if (this.mProvidersByAuthority.get(names[j]) == p) {
                        this.mProvidersByAuthority.remove(names[j]);
                    }
                }
                this.mProvidersByAuthority.remove(p.info.authority);
            }
        }
        int componentSize3 = pkg.receivers.size();
        for (int i3 = 0; i3 < componentSize3; i3++) {
            BPackage.Activity a2 = pkg.receivers.get(i3);
            this.mReceivers.removeActivity(a2, "receiver");
        }
        int componentSize4 = pkg.services.size();
        for (int i4 = 0; i4 < componentSize4; i4++) {
            BPackage.Service s = pkg.services.get(i4);
            this.mServices.removeService(s);
        }
    }

    private void addActivitiesLocked(BPackage pkg, List<BPackage.ActivityIntentInfo> newIntents) {
        int activitiesSize = pkg.activities.size();
        for (int i = 0; i < activitiesSize; i++) {
            BPackage.Activity a = pkg.activities.get(i);
            a.info.processName = BPackageManagerService.fixProcessName(pkg.applicationInfo.processName, a.info.processName);
            this.mActivities.addActivity(a, "activity", newIntents);
        }
    }

    private void addProvidersLocked(BPackage pkg) {
        int providersSize = pkg.providers.size();
        for (int i = 0; i < providersSize; i++) {
            BPackage.Provider p = pkg.providers.get(i);
            p.info.processName = BPackageManagerService.fixProcessName(pkg.applicationInfo.processName, p.info.processName);
            this.mProviders.addProvider(p);
            if (p.info.authority != null) {
                String[] names = p.info.authority.split(";");
                p.info.authority = null;
                for (String name : names) {
                    if (!this.mProvidersByAuthority.containsKey(name)) {
                        this.mProvidersByAuthority.put(name, p);
                        if (p.info.authority == null) {
                            p.info.authority = name;
                        } else {
                            p.info.authority += ";" + name;
                        }
                    } else {
                        BPackage.Provider other = this.mProvidersByAuthority.get(name);
                        ComponentName component = (other == null || other.getComponentName() == null) ? null : other.getComponentName();
                        String packageName = component != null ? component.getPackageName() : "?";
                        Slog.w(TAG, "Skipping provider name " + name + " (in package " + pkg.applicationInfo.packageName + "): name already used by " + packageName);
                    }
                }
            }
        }
    }

    private void addReceiversLocked(BPackage pkg) {
        int receiversSize = pkg.receivers.size();
        for (int i = 0; i < receiversSize; i++) {
            BPackage.Activity a = pkg.receivers.get(i);
            a.info.processName = BPackageManagerService.fixProcessName(pkg.applicationInfo.processName, a.info.processName);
            this.mReceivers.addActivity(a, "receiver", null);
        }
    }

    private void addServicesLocked(BPackage pkg) {
        int servicesSize = pkg.services.size();
        for (int i = 0; i < servicesSize; i++) {
            BPackage.Service s = pkg.services.get(i);
            s.info.processName = BPackageManagerService.fixProcessName(pkg.applicationInfo.processName, s.info.processName);
            this.mServices.addService(s);
        }
    }

    BPackage.Activity getActivity(ComponentName component) {
        BPackage.Activity activity;
        synchronized (this.mLock) {
            activity = this.mActivities.mActivities.get(component);
        }
        return activity;
    }

    BPackage.Provider getProvider(ComponentName component) {
        BPackage.Provider provider;
        synchronized (this.mLock) {
            provider = this.mProviders.mProviders.get(component);
        }
        return provider;
    }

    BPackage.Activity getReceiver(ComponentName component) {
        BPackage.Activity activity;
        synchronized (this.mLock) {
            activity = this.mReceivers.mActivities.get(component);
        }
        return activity;
    }

    BPackage.Service getService(ComponentName component) {
        BPackage.Service service;
        synchronized (this.mLock) {
            service = this.mServices.mServices.get(component);
        }
        return service;
    }

    List<ResolveInfo> queryActivities(Intent intent, String resolvedType, int flags, int userId) {
        List<ResolveInfo> listQueryIntent;
        synchronized (this.mLock) {
            listQueryIntent = this.mActivities.queryIntent(intent, resolvedType, flags, userId);
        }
        return listQueryIntent;
    }

    List<ResolveInfo> queryActivities(Intent intent, String resolvedType, int flags, List<BPackage.Activity> activities, int userId) {
        List<ResolveInfo> listQueryIntentForPackage;
        synchronized (this.mLock) {
            listQueryIntentForPackage = this.mActivities.queryIntentForPackage(intent, resolvedType, flags, activities, userId);
        }
        return listQueryIntentForPackage;
    }

    List<ResolveInfo> queryProviders(Intent intent, String resolvedType, int flags, int userId) {
        List<ResolveInfo> listQueryIntent;
        synchronized (this.mLock) {
            listQueryIntent = this.mProviders.queryIntent(intent, resolvedType, flags, userId);
        }
        return listQueryIntent;
    }

    List<ResolveInfo> queryProviders(Intent intent, String resolvedType, int flags, List<BPackage.Provider> providers, int userId) {
        List<ResolveInfo> listQueryIntentForPackage;
        synchronized (this.mLock) {
            listQueryIntentForPackage = this.mProviders.queryIntentForPackage(intent, resolvedType, flags, providers, userId);
        }
        return listQueryIntentForPackage;
    }

    List<ProviderInfo> queryProviders(String processName, String metaDataKey, int flags, int userId) {
        ProviderInfo info;
        List<ProviderInfo> providerList = new ArrayList<>();
        synchronized (this.mLock) {
            for (int i = this.mProviders.mProviders.size() - 1; i >= 0; i--) {
                BPackage.Provider p = this.mProviders.mProviders.valueAt(i);
                BPackageSettings ps = p.owner.mExtras;
                if (ps != null && p.info.authority != null && ((processName == null || p.info.processName.equals(processName)) && ((metaDataKey == null || (p.metaData != null && p.metaData.containsKey(metaDataKey))) && (info = PackageManagerCompat.generateProviderInfo(p, flags, ps.readUserState(userId), userId)) != null))) {
                    providerList.add(info);
                }
            }
        }
        return providerList;
    }

    ProviderInfo queryProvider(String authority, int flags, int userId) {
        synchronized (this.mLock) {
            BPackage.Provider p = this.mProvidersByAuthority.get(authority);
            if (p == null) {
                return null;
            }
            BPackageSettings ps = p.owner.mExtras;
            return PackageManagerCompat.generateProviderInfo(p, flags, ps.readUserState(userId), userId);
        }
    }

    List<ResolveInfo> queryReceivers(Intent intent, String resolvedType, int flags, int userId) {
        List<ResolveInfo> listQueryIntent;
        synchronized (this.mLock) {
            listQueryIntent = this.mReceivers.queryIntent(intent, resolvedType, flags, userId);
        }
        return listQueryIntent;
    }

    List<ResolveInfo> queryReceivers(Intent intent, String resolvedType, int flags, List<BPackage.Activity> receivers, int userId) {
        List<ResolveInfo> listQueryIntentForPackage;
        synchronized (this.mLock) {
            listQueryIntentForPackage = this.mReceivers.queryIntentForPackage(intent, resolvedType, flags, receivers, userId);
        }
        return listQueryIntentForPackage;
    }

    List<ResolveInfo> queryServices(Intent intent, String resolvedType, int flags, int userId) {
        List<ResolveInfo> listQueryIntent;
        synchronized (this.mLock) {
            listQueryIntent = this.mServices.queryIntent(intent, resolvedType, flags, userId);
        }
        return listQueryIntent;
    }

    List<ResolveInfo> queryServices(Intent intent, String resolvedType, int flags, List<BPackage.Service> services, int userId) {
        List<ResolveInfo> listQueryIntentForPackage;
        synchronized (this.mLock) {
            listQueryIntentForPackage = this.mServices.queryIntentForPackage(intent, resolvedType, flags, services, userId);
        }
        return listQueryIntentForPackage;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/ComponentResolver$ServiceIntentResolver.class */
    private static final class ServiceIntentResolver extends IntentResolver<BPackage.ServiceIntentInfo, ResolveInfo> {
        private final ArrayMap<ComponentName, BPackage.Service> mServices = new ArrayMap<>();
        private int mFlags;

        private ServiceIntentResolver() {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public List<ResolveInfo> queryIntent(Intent intent, String resolvedType, boolean defaultOnly, int userId) {
            this.mFlags = defaultOnly ? 65536 : 0;
            return super.queryIntent(intent, resolvedType, defaultOnly, userId);
        }

        List<ResolveInfo> queryIntent(Intent intent, String resolvedType, int flags, int userId) {
            this.mFlags = flags;
            return super.queryIntent(intent, resolvedType, (flags & 65536) != 0, userId);
        }

        List<ResolveInfo> queryIntentForPackage(Intent intent, String resolvedType, int flags, List<BPackage.Service> packageServices, int userId) {
            if (packageServices == null) {
                return null;
            }
            this.mFlags = flags;
            boolean defaultOnly = (flags & 65536) != 0;
            int servicesSize = packageServices.size();
            ArrayList<BPackage.ServiceIntentInfo[]> listCut = new ArrayList<>(servicesSize);
            for (int i = 0; i < servicesSize; i++) {
                ArrayList<BPackage.ServiceIntentInfo> arrayList = packageServices.get(i).intents;
                if (arrayList != null && arrayList.size() > 0) {
                    BPackage.ServiceIntentInfo[] array = new BPackage.ServiceIntentInfo[arrayList.size()];
                    arrayList.toArray(array);
                    listCut.add(array);
                }
            }
            return super.queryIntentFromList(intent, resolvedType, defaultOnly, listCut, userId);
        }

        void addService(BPackage.Service s) {
            this.mServices.put(s.getComponentName(), s);
            int intentsSize = s.intents.size();
            for (int j = 0; j < intentsSize; j++) {
                BPackage.ServiceIntentInfo intent = (BPackage.ServiceIntentInfo) s.intents.get(j);
                addFilter(intent);
            }
        }

        void removeService(BPackage.Service s) {
            this.mServices.remove(s.getComponentName());
            int intentsSize = s.intents.size();
            for (int j = 0; j < intentsSize; j++) {
                BPackage.ServiceIntentInfo intent = (BPackage.ServiceIntentInfo) s.intents.get(j);
                removeFilter(intent);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public boolean isPackageForFilter(String packageName, BPackage.ServiceIntentInfo info) {
            return packageName.equals(info.service.owner.packageName);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public BPackage.ServiceIntentInfo[] newArray(int size) {
            return new BPackage.ServiceIntentInfo[size];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public ResolveInfo newResult(BPackage.ServiceIntentInfo filter, int match, int userId) {
            BPackage.Service service = filter.service;
            BPackageSettings ps = service.owner.mExtras;
            if (ps == null) {
                return null;
            }
            ServiceInfo si = PackageManagerCompat.generateServiceInfo(service, this.mFlags, ps.readUserState(userId), userId);
            ResolveInfo res = new ResolveInfo();
            res.serviceInfo = si;
            if ((this.mFlags & 64) != 0) {
                res.filter = filter.intentFilter;
            }
            res.priority = filter.intentFilter.getPriority();
            res.preferredOrder = service.owner.mPreferredOrder;
            res.match = match;
            res.isDefault = filter.hasDefault;
            res.labelRes = filter.labelRes;
            res.nonLocalizedLabel = filter.nonLocalizedLabel;
            res.icon = filter.icon;
            return res;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/ComponentResolver$ActivityIntentResolver.class */
    private static final class ActivityIntentResolver extends IntentResolver<BPackage.ActivityIntentInfo, ResolveInfo> {
        private final ArrayMap<ComponentName, BPackage.Activity> mActivities = new ArrayMap<>();
        private int mFlags;

        private ActivityIntentResolver() {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public List<ResolveInfo> queryIntent(Intent intent, String resolvedType, boolean defaultOnly, int userId) {
            this.mFlags = defaultOnly ? 65536 : 0;
            return super.queryIntent(intent, resolvedType, defaultOnly, userId);
        }

        List<ResolveInfo> queryIntent(Intent intent, String resolvedType, int flags, int userId) {
            this.mFlags = flags;
            return super.queryIntent(intent, resolvedType, (flags & 65536) != 0, userId);
        }

        List<ResolveInfo> queryIntentForPackage(Intent intent, String resolvedType, int flags, List<BPackage.Activity> packageActivities, int userId) {
            if (packageActivities == null) {
                return null;
            }
            this.mFlags = flags;
            boolean defaultOnly = (flags & 65536) != 0;
            int activitiesSize = packageActivities.size();
            ArrayList<BPackage.ActivityIntentInfo[]> listCut = new ArrayList<>(activitiesSize);
            for (int i = 0; i < activitiesSize; i++) {
                ArrayList<BPackage.ActivityIntentInfo> arrayList = packageActivities.get(i).intents;
                if (arrayList != null && arrayList.size() > 0) {
                    BPackage.ActivityIntentInfo[] array = new BPackage.ActivityIntentInfo[arrayList.size()];
                    arrayList.toArray(array);
                    listCut.add(array);
                }
            }
            return super.queryIntentFromList(intent, resolvedType, defaultOnly, listCut, userId);
        }

        private void addActivity(BPackage.Activity a, String type, List<BPackage.ActivityIntentInfo> newIntents) {
            this.mActivities.put(a.getComponentName(), a);
            int intentsSize = a.intents.size();
            for (int j = 0; j < intentsSize; j++) {
                BPackage.ActivityIntentInfo intent = (BPackage.ActivityIntentInfo) a.intents.get(j);
                if (newIntents != null && "activity".equals(type)) {
                    newIntents.add(intent);
                }
                addFilter(intent);
            }
        }

        private void removeActivity(BPackage.Activity a, String type) {
            this.mActivities.remove(a.getComponentName());
            int intentsSize = a.intents.size();
            for (int j = 0; j < intentsSize; j++) {
                BPackage.ActivityIntentInfo intent = (BPackage.ActivityIntentInfo) a.intents.get(j);
                removeFilter(intent);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public boolean isPackageForFilter(String packageName, BPackage.ActivityIntentInfo info) {
            return packageName.equals(info.activity.owner.packageName);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public BPackage.ActivityIntentInfo[] newArray(int size) {
            return new BPackage.ActivityIntentInfo[size];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public ResolveInfo newResult(BPackage.ActivityIntentInfo info, int match, int userId) {
            BPackage.Activity activity = info.activity;
            BPackageSettings ps = activity.owner.mExtras;
            if (ps == null) {
                return null;
            }
            ActivityInfo ai = PackageManagerCompat.generateActivityInfo(activity, this.mFlags, ps.readUserState(userId), userId);
            ResolveInfo res = new ResolveInfo();
            res.activityInfo = ai;
            if ((this.mFlags & 64) != 0) {
                res.filter = info.intentFilter;
            }
            res.priority = info.intentFilter.getPriority();
            res.preferredOrder = activity.owner.mPreferredOrder;
            res.match = match;
            res.isDefault = info.hasDefault;
            res.labelRes = info.labelRes;
            res.nonLocalizedLabel = info.nonLocalizedLabel;
            res.icon = info.icon;
            return res;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/ComponentResolver$ProviderIntentResolver.class */
    private static final class ProviderIntentResolver extends IntentResolver<BPackage.ProviderIntentInfo, ResolveInfo> {
        private final ArrayMap<ComponentName, BPackage.Provider> mProviders = new ArrayMap<>();
        private int mFlags;

        private ProviderIntentResolver() {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public List<ResolveInfo> queryIntent(Intent intent, String resolvedType, boolean defaultOnly, int userId) {
            this.mFlags = defaultOnly ? 65536 : 0;
            return super.queryIntent(intent, resolvedType, defaultOnly, userId);
        }

        List<ResolveInfo> queryIntent(Intent intent, String resolvedType, int flags, int userId) {
            this.mFlags = flags;
            return super.queryIntent(intent, resolvedType, (flags & 65536) != 0, userId);
        }

        List<ResolveInfo> queryIntentForPackage(Intent intent, String resolvedType, int flags, List<BPackage.Provider> packageProviders, int userId) {
            if (packageProviders == null) {
                return null;
            }
            this.mFlags = flags;
            boolean defaultOnly = (flags & 65536) != 0;
            int providersSize = packageProviders.size();
            ArrayList<BPackage.ProviderIntentInfo[]> listCut = new ArrayList<>(providersSize);
            for (int i = 0; i < providersSize; i++) {
                ArrayList<BPackage.ProviderIntentInfo> arrayList = packageProviders.get(i).intents;
                if (arrayList != null && arrayList.size() > 0) {
                    BPackage.ProviderIntentInfo[] array = new BPackage.ProviderIntentInfo[arrayList.size()];
                    arrayList.toArray(array);
                    listCut.add(array);
                }
            }
            return super.queryIntentFromList(intent, resolvedType, defaultOnly, listCut, userId);
        }

        void addProvider(BPackage.Provider p) {
            this.mProviders.put(p.getComponentName(), p);
            int intentsSize = p.intents.size();
            for (int j = 0; j < intentsSize; j++) {
                BPackage.ProviderIntentInfo intent = (BPackage.ProviderIntentInfo) p.intents.get(j);
                addFilter(intent);
            }
        }

        void removeProvider(BPackage.Provider p) {
            this.mProviders.remove(p.getComponentName());
            int intentsSize = p.intents.size();
            for (int j = 0; j < intentsSize; j++) {
                BPackage.ProviderIntentInfo intent = (BPackage.ProviderIntentInfo) p.intents.get(j);
                removeFilter(intent);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public boolean allowFilterResult(BPackage.ProviderIntentInfo filter, List<ResolveInfo> dest) {
            ProviderInfo filterPi = filter.provider.info;
            for (int i = dest.size() - 1; i >= 0; i--) {
                ProviderInfo destPi = dest.get(i).providerInfo;
                if (destPi.name.equals(filterPi.name) && destPi.packageName.equals(filterPi.packageName)) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public BPackage.ProviderIntentInfo[] newArray(int size) {
            return new BPackage.ProviderIntentInfo[size];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public boolean isPackageForFilter(String packageName, BPackage.ProviderIntentInfo info) {
            return packageName.equals(info.provider.owner.packageName);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // top.niunaijun.blackbox.core.system.pm.IntentResolver
        public ResolveInfo newResult(BPackage.ProviderIntentInfo filter, int match, int userId) {
            BPackage.Provider provider = filter.provider;
            BPackageSettings ps = provider.owner.mExtras;
            if (ps == null) {
                return null;
            }
            ProviderInfo pi = PackageManagerCompat.generateProviderInfo(provider, this.mFlags, ps.readUserState(userId), userId);
            ResolveInfo res = new ResolveInfo();
            res.providerInfo = pi;
            if ((this.mFlags & 64) != 0) {
                res.filter = filter.intentFilter;
            }
            res.priority = filter.intentFilter.getPriority();
            res.preferredOrder = provider.owner.mPreferredOrder;
            res.match = match;
            res.isDefault = filter.hasDefault;
            res.labelRes = filter.labelRes;
            res.nonLocalizedLabel = filter.nonLocalizedLabel;
            res.icon = filter.icon;
            return res;
        }
    }
}
