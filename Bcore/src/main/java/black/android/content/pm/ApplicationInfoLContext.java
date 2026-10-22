package black.android.content.pm;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/ApplicationInfoLContext.class */
@BClassNameNotProcess("android.content.pm.ApplicationInfo")
public interface ApplicationInfoLContext {
    @BFieldSetNotProcess
    void _set_primaryCpuAbi(Object obj);

    @BFieldCheckNotProcess
    Field _check_primaryCpuAbi();

    @BFieldNotProcess
    String primaryCpuAbi();

    @BFieldSetNotProcess
    void _set_privateFlags(Object obj);

    @BFieldCheckNotProcess
    Field _check_privateFlags();

    @BFieldNotProcess
    Integer privateFlags();

    @BFieldSetNotProcess
    void _set_scanPublicSourceDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_scanPublicSourceDir();

    @BFieldNotProcess
    String scanPublicSourceDir();

    @BFieldSetNotProcess
    void _set_scanSourceDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_scanSourceDir();

    @BFieldNotProcess
    String scanSourceDir();

    @BFieldSetNotProcess
    void _set_secondaryCpuAbi(Object obj);

    @BFieldCheckNotProcess
    Field _check_secondaryCpuAbi();

    @BFieldNotProcess
    String secondaryCpuAbi();

    @BFieldSetNotProcess
    void _set_secondaryNativeLibraryDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_secondaryNativeLibraryDir();

    @BFieldNotProcess
    String secondaryNativeLibraryDir();

    @BFieldSetNotProcess
    void _set_splitPublicSourceDirs(Object obj);

    @BFieldCheckNotProcess
    Field _check_splitPublicSourceDirs();

    @BFieldNotProcess
    String[] splitPublicSourceDirs();

    @BFieldSetNotProcess
    void _set_splitSourceDirs(Object obj);

    @BFieldCheckNotProcess
    Field _check_splitSourceDirs();

    @BFieldNotProcess
    String[] splitSourceDirs();
}
