package android.content.pm;

import android.util.ArraySet;

/* JADX INFO: loaded from: classes.jar:android/content/pm/PackageUserState.class */
public class PackageUserState {
    public boolean stopped;
    public boolean notLaunched;
    public boolean installed;
    public boolean hidden;
    public int enabled;
    public boolean blockUninstall;
    public String lastDisableAppCaller;
    public ArraySet<String> disabledComponents;
    public ArraySet<String> enabledComponents;
    public int domainVerificationStatus;
    public int appLinkGeneration;

    public PackageUserState() {
        throw new RuntimeException("Stub!");
    }

    public PackageUserState(PackageUserState o) {
        throw new RuntimeException("Stub!");
    }
}
