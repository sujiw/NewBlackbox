package black.android.content.pm;

import android.content.pm.Signature;
import android.os.Bundle;
import java.lang.reflect.Field;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParserPackageContext.class */
@BClassNameNotProcess("android.content.pm.PackageParser$Package")
public interface PackageParserPackageContext {
    @BFieldSetNotProcess
    void _set_activities(Object obj);

    @BFieldCheckNotProcess
    Field _check_activities();

    @BFieldNotProcess
    List activities();

    @BFieldSetNotProcess
    void _set_mAppMetaData(Object obj);

    @BFieldCheckNotProcess
    Field _check_mAppMetaData();

    @BFieldNotProcess
    Bundle mAppMetaData();

    @BFieldSetNotProcess
    void _set_mSharedUserId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSharedUserId();

    @BFieldNotProcess
    String mSharedUserId();

    @BFieldSetNotProcess
    void _set_mSignatures(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSignatures();

    @BFieldNotProcess
    Signature[] mSignatures();

    @BFieldSetNotProcess
    void _set_mSigningDetails(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSigningDetails();

    @BFieldNotProcess
    Object mSigningDetails();

    @BFieldSetNotProcess
    void _set_mVersionCode(Object obj);

    @BFieldCheckNotProcess
    Field _check_mVersionCode();

    @BFieldNotProcess
    Integer mVersionCode();

    @BFieldSetNotProcess
    void _set_packageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_packageName();

    @BFieldNotProcess
    String packageName();

    @BFieldSetNotProcess
    void _set_permissionGroups(Object obj);

    @BFieldCheckNotProcess
    Field _check_permissionGroups();

    @BFieldNotProcess
    List permissionGroups();

    @BFieldSetNotProcess
    void _set_permissions(Object obj);

    @BFieldCheckNotProcess
    Field _check_permissions();

    @BFieldNotProcess
    List permissions();

    @BFieldSetNotProcess
    void _set_protectedBroadcasts(Object obj);

    @BFieldCheckNotProcess
    Field _check_protectedBroadcasts();

    @BFieldNotProcess
    List<String> protectedBroadcasts();

    @BFieldSetNotProcess
    void _set_providers(Object obj);

    @BFieldCheckNotProcess
    Field _check_providers();

    @BFieldNotProcess
    List providers();

    @BFieldSetNotProcess
    void _set_receivers(Object obj);

    @BFieldCheckNotProcess
    Field _check_receivers();

    @BFieldNotProcess
    List receivers();

    @BFieldSetNotProcess
    void _set_requestedPermissions(Object obj);

    @BFieldCheckNotProcess
    Field _check_requestedPermissions();

    @BFieldNotProcess
    List<String> requestedPermissions();

    @BFieldSetNotProcess
    void _set_services(Object obj);

    @BFieldCheckNotProcess
    Field _check_services();

    @BFieldNotProcess
    List services();
}
