package top.niunaijun.blackbox.core.system.pm;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageParser;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage.class */
public class BPackage implements Parcelable {
    public ArrayList<Activity> activities;
    public ArrayList<Activity> receivers;
    public ArrayList<Provider> providers;
    public ArrayList<Service> services;
    public ArrayList<Instrumentation> instrumentation;
    public ArrayList<Permission> permissions;
    public ArrayList<PermissionGroup> permissionGroups;
    public ArrayList<String> requestedPermissions;
    public Signature[] mSignatures;
    public SigningDetails mSigningDetails;
    public Bundle mAppMetaData;
    public BPackageSettings mExtras;
    public String packageName;
    public int mPreferredOrder;
    public String mSharedUserId;
    public ArrayList<String> usesLibraries;
    public ArrayList<String> usesOptionalLibraries;
    public int mVersionCode;
    public ApplicationInfo applicationInfo;
    public String mVersionName;
    public String baseCodePath;
    public int mSharedUserLabel;
    public ArrayList<ConfigurationInfo> configPreferences;
    public ArrayList<FeatureInfo> reqFeatures;
    public InstallOption installOption;
    public static final Parcelable.Creator<BPackage> CREATOR = new Parcelable.Creator<BPackage>() { // from class: top.niunaijun.blackbox.core.system.pm.BPackage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BPackage createFromParcel(Parcel source) {
            return new BPackage(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BPackage[] newArray(int size) {
            return new BPackage[size];
        }
    };

    public BPackage(PackageParser.Package aPackage) {
        this.activities = new ArrayList<>(0);
        this.receivers = new ArrayList<>(0);
        this.providers = new ArrayList<>(0);
        this.services = new ArrayList<>(0);
        this.instrumentation = new ArrayList<>(0);
        this.permissions = new ArrayList<>(0);
        this.permissionGroups = new ArrayList<>(0);
        this.requestedPermissions = new ArrayList<>();
        this.configPreferences = null;
        this.reqFeatures = null;
        this.activities = new ArrayList<>(aPackage.activities.size());
        for (PackageParser.Activity activity : aPackage.activities) {
            Activity selfActivity = new Activity(activity);
            for (ActivityIntentInfo intent : selfActivity.intents) {
                intent.activity = selfActivity;
            }
            selfActivity.owner = this;
            this.activities.add(selfActivity);
        }
        this.receivers = new ArrayList<>(aPackage.receivers.size());
        for (PackageParser.Activity receiver : aPackage.receivers) {
            Activity selfReceiver = new Activity(receiver);
            for (ActivityIntentInfo intent2 : selfReceiver.intents) {
                intent2.activity = selfReceiver;
            }
            selfReceiver.owner = this;
            this.receivers.add(selfReceiver);
        }
        this.providers = new ArrayList<>(aPackage.providers.size());
        for (PackageParser.Provider provider : aPackage.providers) {
            Provider selfProvider = new Provider(provider);
            for (ProviderIntentInfo intent3 : selfProvider.intents) {
                intent3.provider = selfProvider;
            }
            selfProvider.owner = this;
            this.providers.add(selfProvider);
        }
        this.services = new ArrayList<>(aPackage.services.size());
        for (PackageParser.Service service : aPackage.services) {
            Service selfService = new Service(service);
            for (ServiceIntentInfo intent4 : selfService.intents) {
                intent4.service = selfService;
            }
            selfService.owner = this;
            this.services.add(selfService);
        }
        this.instrumentation = new ArrayList<>(aPackage.instrumentation.size());
        for (PackageParser.Instrumentation instrumentation1 : aPackage.instrumentation) {
            Instrumentation selfInstrumentation = new Instrumentation(instrumentation1);
            selfInstrumentation.owner = this;
            this.instrumentation.add(selfInstrumentation);
        }
        this.permissions = new ArrayList<>(aPackage.permissions.size());
        for (PackageParser.Permission permission : aPackage.permissions) {
            Permission selfPermission = new Permission(permission);
            selfPermission.owner = this;
            this.permissions.add(selfPermission);
        }
        this.permissionGroups = new ArrayList<>(aPackage.permissionGroups.size());
        for (PackageParser.PermissionGroup permissionGroup : aPackage.permissionGroups) {
            PermissionGroup selfPermissionGroup = new PermissionGroup(permissionGroup);
            selfPermissionGroup.owner = this;
            this.permissionGroups.add(selfPermissionGroup);
        }
        this.requestedPermissions = aPackage.requestedPermissions;
        if (BuildCompat.isPie()) {
            this.mSigningDetails = new SigningDetails(aPackage.mSigningDetails);
            this.mSignatures = this.mSigningDetails.signatures;
        } else {
            this.mSignatures = aPackage.mSignatures;
        }
        this.mAppMetaData = aPackage.mAppMetaData;
        this.packageName = aPackage.packageName;
        this.mPreferredOrder = aPackage.mPreferredOrder;
        this.mSharedUserId = aPackage.mSharedUserId;
        this.usesLibraries = aPackage.usesLibraries;
        this.usesOptionalLibraries = aPackage.usesOptionalLibraries;
        this.mVersionCode = aPackage.mVersionCode;
        this.applicationInfo = aPackage.applicationInfo;
        this.mVersionName = aPackage.mVersionName;
        this.baseCodePath = aPackage.baseCodePath;
        this.mSharedUserLabel = aPackage.mSharedUserLabel;
        this.configPreferences = aPackage.configPreferences;
        this.reqFeatures = aPackage.reqFeatures;
    }

    protected BPackage(Parcel in) {
        this.activities = new ArrayList<>(0);
        this.receivers = new ArrayList<>(0);
        this.providers = new ArrayList<>(0);
        this.services = new ArrayList<>(0);
        this.instrumentation = new ArrayList<>(0);
        this.permissions = new ArrayList<>(0);
        this.permissionGroups = new ArrayList<>(0);
        this.requestedPermissions = new ArrayList<>();
        this.configPreferences = null;
        this.reqFeatures = null;
        int N = in.readInt();
        this.activities = new ArrayList<>(N);
        while (true) {
            int i = N;
            N--;
            if (i <= 0) {
                break;
            }
            Activity activity = new Activity(in);
            for (ActivityIntentInfo intent : activity.intents) {
                intent.activity = activity;
            }
            activity.owner = this;
            this.activities.add(activity);
        }
        int N2 = in.readInt();
        this.receivers = new ArrayList<>(N2);
        while (true) {
            int i2 = N2;
            N2--;
            if (i2 <= 0) {
                break;
            }
            Activity activity2 = new Activity(in);
            for (ActivityIntentInfo intent2 : activity2.intents) {
                intent2.activity = activity2;
            }
            activity2.owner = this;
            this.receivers.add(activity2);
        }
        int N3 = in.readInt();
        this.providers = new ArrayList<>(N3);
        while (true) {
            int i3 = N3;
            N3--;
            if (i3 <= 0) {
                break;
            }
            Provider provider = new Provider(in);
            for (ProviderIntentInfo intent3 : provider.intents) {
                intent3.provider = provider;
            }
            provider.owner = this;
            this.providers.add(provider);
        }
        int N4 = in.readInt();
        this.services = new ArrayList<>(N4);
        while (true) {
            int i4 = N4;
            N4--;
            if (i4 <= 0) {
                break;
            }
            Service service = new Service(in);
            for (ServiceIntentInfo intent4 : service.intents) {
                intent4.service = service;
            }
            service.owner = this;
            this.services.add(service);
        }
        int N5 = in.readInt();
        this.instrumentation = new ArrayList<>(N5);
        while (true) {
            int i5 = N5;
            N5--;
            if (i5 <= 0) {
                break;
            }
            Instrumentation instrumentation = new Instrumentation(in);
            instrumentation.owner = this;
            this.instrumentation.add(instrumentation);
        }
        int N6 = in.readInt();
        this.permissions = new ArrayList<>(N6);
        while (true) {
            int i6 = N6;
            N6--;
            if (i6 <= 0) {
                break;
            }
            Permission permission = new Permission(in);
            permission.owner = this;
            this.permissions.add(permission);
        }
        int N7 = in.readInt();
        this.permissionGroups = new ArrayList<>(N7);
        while (true) {
            int i7 = N7;
            N7--;
            if (i7 <= 0) {
                break;
            }
            PermissionGroup permissionGroup = new PermissionGroup(in);
            permissionGroup.owner = this;
            this.permissionGroups.add(permissionGroup);
        }
        in.readStringList(this.requestedPermissions);
        if (BuildCompat.isPie()) {
            this.mSigningDetails = (SigningDetails) in.readParcelable(SigningDetails.class.getClassLoader());
        }
        this.mSignatures = (Signature[]) in.createTypedArray(Signature.CREATOR);
        this.mAppMetaData = in.readBundle(Bundle.class.getClassLoader());
        this.packageName = in.readString();
        this.mPreferredOrder = in.readInt();
        this.mSharedUserId = in.readString();
        this.usesLibraries = in.createStringArrayList();
        this.usesOptionalLibraries = in.createStringArrayList();
        this.mVersionCode = in.readInt();
        this.applicationInfo = (ApplicationInfo) in.readParcelable(ApplicationInfo.class.getClassLoader());
        this.mVersionName = in.readString();
        this.baseCodePath = in.readString();
        this.mSharedUserLabel = in.readInt();
        this.configPreferences = in.createTypedArrayList(ConfigurationInfo.CREATOR);
        this.reqFeatures = in.createTypedArrayList(FeatureInfo.CREATOR);
        this.installOption = (InstallOption) in.readParcelable(InstallOption.class.getClassLoader());
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$Activity.class */
    public static final class Activity extends Component<ActivityIntentInfo> {
        public ActivityInfo info;

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Activity(PackageParser.Activity activity) {
            super(activity);
            this.info = activity.info;
            if (activity.intents != null) {
                this.intents = new ArrayList<>(activity.intents.size());
                Iterator it = activity.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new ActivityIntentInfo((PackageParser.ActivityIntentInfo) it.next()));
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Activity(Parcel parcel) {
            super(parcel);
            this.info = (ActivityInfo) parcel.readParcelable(ActivityInfo.class.getClassLoader());
            int i = parcel.readInt();
            this.intents = new ArrayList<>(i);
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    this.intents.add(new ActivityIntentInfo((IntentInfo) parcel.readParcelable(BPackage.class.getClassLoader())));
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$Service.class */
    public static final class Service extends Component<ServiceIntentInfo> {
        public ServiceInfo info;

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Service(PackageParser.Service service) {
            super(service);
            this.info = service.info;
            if (service.intents != null) {
                this.intents = new ArrayList<>(service.intents.size());
                Iterator it = service.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new ServiceIntentInfo((PackageParser.ServiceIntentInfo) it.next()));
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Service(Parcel parcel) {
            super(parcel);
            this.info = (ServiceInfo) parcel.readParcelable(ServiceInfo.class.getClassLoader());
            int i = parcel.readInt();
            this.intents = new ArrayList<>(i);
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    this.intents.add(new ServiceIntentInfo((IntentInfo) parcel.readParcelable(BPackage.class.getClassLoader())));
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$Provider.class */
    public static final class Provider extends Component<ProviderIntentInfo> {
        public ProviderInfo info;

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Provider(PackageParser.Provider provider) {
            super(provider);
            this.info = provider.info;
            if (provider.intents != null) {
                this.intents = new ArrayList<>(provider.intents.size());
                Iterator it = provider.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new ProviderIntentInfo((PackageParser.ProviderIntentInfo) it.next()));
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Provider(Parcel parcel) {
            super(parcel);
            this.info = (ProviderInfo) parcel.readParcelable(ProviderInfo.class.getClassLoader());
            int i = parcel.readInt();
            this.intents = new ArrayList<>(i);
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    this.intents.add(new ProviderIntentInfo((IntentInfo) parcel.readParcelable(BPackage.class.getClassLoader())));
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$Instrumentation.class */
    public static final class Instrumentation extends Component<IntentInfo> {
        public InstrumentationInfo info;

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Instrumentation(PackageParser.Instrumentation instrumentation) {
            super(instrumentation);
            this.info = instrumentation.info;
            if (instrumentation.intents != null) {
                this.intents = new ArrayList<>(instrumentation.intents.size());
                Iterator it = instrumentation.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new IntentInfo((PackageParser.IntentInfo) it.next()));
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Instrumentation(Parcel parcel) {
            super(parcel);
            this.info = (InstrumentationInfo) parcel.readParcelable(InstrumentationInfo.class.getClassLoader());
            int i = parcel.readInt();
            this.intents = new ArrayList<>(i);
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    this.intents.add(((IntentInfo) parcel.readParcelable(BPackage.class.getClassLoader())));
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$Permission.class */
    public static final class Permission extends Component<IntentInfo> {
        public PermissionInfo info;

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Permission(PackageParser.Permission permission) {
            super(permission);
            this.info = permission.info;
            if (permission.intents != null) {
                this.intents = new ArrayList<>(permission.intents.size());
                Iterator it = permission.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new IntentInfo((PackageParser.IntentInfo) it.next()));
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Permission(Parcel parcel) {
            super(parcel);
            this.info = (PermissionInfo) parcel.readParcelable(Permission.class.getClassLoader());
            int i = parcel.readInt();
            this.intents = new ArrayList<>(i);
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    this.intents.add(((IntentInfo) parcel.readParcelable(BPackage.class.getClassLoader())));
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$PermissionGroup.class */
    public static final class PermissionGroup extends Component<IntentInfo> {
        public PermissionGroupInfo info;

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public PermissionGroup(PackageParser.PermissionGroup permissionGroup) {
            super(permissionGroup);
            this.info = permissionGroup.info;
            if (permissionGroup.intents != null) {
                this.intents = new ArrayList<>(permissionGroup.intents.size());
                Iterator it = permissionGroup.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new IntentInfo((PackageParser.IntentInfo) it.next()));
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public PermissionGroup(Parcel parcel) {
            super(parcel);
            this.info = (PermissionGroupInfo) parcel.readParcelable(PermissionGroup.class.getClassLoader());
            int i = parcel.readInt();
            this.intents = new ArrayList<>(i);
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    this.intents.add(((IntentInfo) parcel.readParcelable(BPackage.class.getClassLoader())));
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$ActivityIntentInfo.class */
    public static class ActivityIntentInfo extends IntentInfo {
        public Activity activity;

        public ActivityIntentInfo(PackageParser.IntentInfo intentInfo) {
            super(intentInfo);
        }

        public ActivityIntentInfo(IntentInfo intentInfo) {
            super(intentInfo);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$ServiceIntentInfo.class */
    public static class ServiceIntentInfo extends IntentInfo {
        public Service service;

        public ServiceIntentInfo(PackageParser.IntentInfo intentInfo) {
            super(intentInfo);
        }

        public ServiceIntentInfo(IntentInfo intentInfo) {
            super(intentInfo);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$ProviderIntentInfo.class */
    public static class ProviderIntentInfo extends IntentInfo {
        public Provider provider;

        public ProviderIntentInfo(PackageParser.IntentInfo intentInfo) {
            super(intentInfo);
        }

        public ProviderIntentInfo(IntentInfo intentInfo) {
            super(intentInfo);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$SigningDetails.class */
    public static final class SigningDetails implements Parcelable {
        public Signature[] signatures;
        public static final PackageParser.SigningDetails UNKNOWN = null;
        public static final Parcelable.Creator<SigningDetails> CREATOR = new Parcelable.Creator<SigningDetails>() { // from class: top.niunaijun.blackbox.core.system.pm.BPackage.SigningDetails.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SigningDetails createFromParcel(Parcel source) {
                return new SigningDetails(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SigningDetails[] newArray(int size) {
                return new SigningDetails[size];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedArray(this.signatures, flags);
        }

        public SigningDetails(PackageParser.SigningDetails signingDetails) {
            if (signingDetails.pastSigningCertificates == null) {
                this.signatures = signingDetails.signatures;
            } else {
                this.signatures = signingDetails.pastSigningCertificates;
            }
        }

        protected SigningDetails(Parcel in) {
            this.signatures = (Signature[]) in.createTypedArray(Signature.CREATOR);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$IntentInfo.class */
    public static class IntentInfo implements Parcelable {
        public IntentFilter intentFilter;
        public boolean hasDefault;
        public int labelRes;
        public String nonLocalizedLabel;
        public int icon;
        public int logo;
        public int banner;
        public static final Parcelable.Creator<IntentInfo> CREATOR = new Parcelable.Creator<IntentInfo>() { // from class: top.niunaijun.blackbox.core.system.pm.BPackage.IntentInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public IntentInfo createFromParcel(Parcel source) {
                return new IntentInfo(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public IntentInfo[] newArray(int size) {
                return new IntentInfo[size];
            }
        };

        public IntentInfo(PackageParser.IntentInfo intentInfo) {
            this.intentFilter = intentInfo;
            this.hasDefault = intentInfo.hasDefault;
            this.labelRes = intentInfo.labelRes;
            this.nonLocalizedLabel = intentInfo.nonLocalizedLabel == null ? null : intentInfo.nonLocalizedLabel.toString();
            this.icon = intentInfo.icon;
            this.logo = intentInfo.logo;
            this.banner = intentInfo.banner;
        }

        public IntentInfo(IntentInfo intentInfo) {
            this.intentFilter = intentInfo.intentFilter;
            this.hasDefault = intentInfo.hasDefault;
            this.labelRes = intentInfo.labelRes;
            this.nonLocalizedLabel = intentInfo.nonLocalizedLabel == null ? null : intentInfo.nonLocalizedLabel.toString();
            this.icon = intentInfo.icon;
            this.logo = intentInfo.logo;
            this.banner = intentInfo.banner;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.intentFilter, flags);
            dest.writeByte(this.hasDefault ? (byte) 1 : (byte) 0);
            dest.writeInt(this.labelRes);
            dest.writeString(this.nonLocalizedLabel);
            dest.writeInt(this.icon);
            dest.writeInt(this.logo);
            dest.writeInt(this.banner);
        }

        protected IntentInfo(Parcel in) {
            this.intentFilter = (IntentFilter) in.readParcelable(BPackage.class.getClassLoader());
            this.hasDefault = in.readByte() != 0;
            this.labelRes = in.readInt();
            this.nonLocalizedLabel = in.readString();
            this.icon = in.readInt();
            this.logo = in.readInt();
            this.banner = in.readInt();
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackage$Component.class */
    public static class Component<II extends IntentInfo> {
        public BPackage owner;
        public ArrayList<II> intents;
        public String className;
        public Bundle metaData;
        public ComponentName componentName;

        public Component(Parcel parcel) {
            this.className = parcel.readString();
            this.metaData = parcel.readBundle(Bundle.class.getClassLoader());
        }

        public Component(PackageParser.Component<?> component) {
            this.className = component.className;
            this.metaData = component.metaData;
        }

        public ComponentName getComponentName() {
            if (this.componentName != null) {
                return this.componentName;
            }
            if (this.className != null) {
                this.componentName = new ComponentName(this.owner.packageName, this.className);
            }
            return this.componentName;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        int size = this.activities.size();
        dest.writeInt(size);
        for (Activity activity : this.activities) {
            dest.writeString(activity.className);
            dest.writeBundle(activity.metaData);
            dest.writeParcelable(activity.info, flags);
            if (activity.intents != null) {
                int N = activity.intents.size();
                dest.writeInt(N);
                for (int i = N - 1; i >= 0; i--) {
                        dest.writeParcelable((Parcelable) activity.intents.get(i), flags);
                    }
            } else {
                dest.writeInt(0);
            }
        }
        int size2 = this.receivers.size();
        dest.writeInt(size2);
        for (Activity receiver : this.receivers) {
            dest.writeString(receiver.className);
            dest.writeBundle(receiver.metaData);
            dest.writeParcelable(receiver.info, flags);
            if (receiver.intents != null) {
                int N2 = receiver.intents.size();
                dest.writeInt(N2);
                for (int i = N2 - 1; i >= 0; i--) {
                        dest.writeParcelable((Parcelable) receiver.intents.get(i), flags);
                    }
            } else {
                dest.writeInt(0);
            }
        }
        int size3 = this.providers.size();
        dest.writeInt(size3);
        for (Provider provider : this.providers) {
            dest.writeString(provider.className);
            dest.writeBundle(provider.metaData);
            dest.writeParcelable(provider.info, flags);
            if (provider.intents != null) {
                int N3 = provider.intents.size();
                dest.writeInt(N3);
                for (int i = N3 - 1; i >= 0; i--) {
                        dest.writeParcelable((Parcelable) provider.intents.get(i), flags);
                    }
            } else {
                dest.writeInt(0);
            }
        }
        int size4 = this.services.size();
        dest.writeInt(size4);
        for (Service service : this.services) {
            dest.writeString(service.className);
            dest.writeBundle(service.metaData);
            dest.writeParcelable(service.info, flags);
            if (service.intents != null) {
                int N4 = service.intents.size();
                dest.writeInt(N4);
                for (int i = N4 - 1; i >= 0; i--) {
                        dest.writeParcelable((Parcelable) service.intents.get(i), flags);
                    }
            } else {
                dest.writeInt(0);
            }
        }
        int size5 = this.instrumentation.size();
        dest.writeInt(size5);
        for (Instrumentation instrumentation : this.instrumentation) {
            dest.writeString(instrumentation.className);
            dest.writeBundle(instrumentation.metaData);
            dest.writeParcelable(instrumentation.info, flags);
            if (instrumentation.intents != null) {
                int N5 = instrumentation.intents.size();
                dest.writeInt(N5);
                for (int i = N5 - 1; i >= 0; i--) {
                        dest.writeParcelable((Parcelable) instrumentation.intents.get(i), flags);
                    }
            } else {
                dest.writeInt(0);
            }
        }
        int size6 = this.permissions.size();
        dest.writeInt(size6);
        for (Permission permission : this.permissions) {
            dest.writeString(permission.className);
            dest.writeBundle(permission.metaData);
            dest.writeParcelable(permission.info, flags);
            if (permission.intents != null) {
                int N6 = permission.intents.size();
                dest.writeInt(N6);
                for (int i = N6 - 1; i >= 0; i--) {
                        dest.writeParcelable((Parcelable) permission.intents.get(i), flags);
                    }
            } else {
                dest.writeInt(0);
            }
        }
        int size7 = this.permissionGroups.size();
        dest.writeInt(size7);
        for (PermissionGroup permissionGroup : this.permissionGroups) {
            dest.writeString(permissionGroup.className);
            dest.writeBundle(permissionGroup.metaData);
            dest.writeParcelable(permissionGroup.info, flags);
            if (permissionGroup.intents != null) {
                int N7 = permissionGroup.intents.size();
                dest.writeInt(N7);
                for (int i = N7 - 1; i >= 0; i--) {
                        dest.writeParcelable((Parcelable) permissionGroup.intents.get(i), flags);
                    }
            } else {
                dest.writeInt(0);
            }
        }
        dest.writeStringList(this.requestedPermissions);
        if (BuildCompat.isPie()) {
            dest.writeParcelable(this.mSigningDetails, flags);
        }
        dest.writeTypedArray(this.mSignatures, flags);
        dest.writeBundle(this.mAppMetaData);
        dest.writeString(this.packageName);
        dest.writeInt(this.mPreferredOrder);
        dest.writeString(this.mSharedUserId);
        dest.writeStringList(this.usesLibraries);
        dest.writeStringList(this.usesOptionalLibraries);
        dest.writeInt(this.mVersionCode);
        dest.writeParcelable(this.applicationInfo, flags);
        dest.writeString(this.mVersionName);
        dest.writeString(this.baseCodePath);
        dest.writeInt(this.mSharedUserLabel);
        dest.writeTypedList(this.configPreferences);
        dest.writeTypedList(this.reqFeatures);
        dest.writeParcelable(this.installOption, flags);
    }
}
