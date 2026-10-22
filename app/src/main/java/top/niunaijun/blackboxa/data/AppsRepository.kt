package top.niunaijun.blackboxa.data

import android.content.pm.ApplicationInfo
import android.net.Uri
import android.util.Log
import android.webkit.URLUtil
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackbox.BlackBoxCore.getPackageManager
import top.niunaijun.blackbox.utils.AbiUtils
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.app.App
import top.niunaijun.blackboxa.app.AppManager
import top.niunaijun.blackboxa.bean.AppDeviceStore
import top.niunaijun.blackboxa.bean.AppGaidStore
import top.niunaijun.blackboxa.bean.AppInfo
import top.niunaijun.blackboxa.bean.AppProxyStore
import top.niunaijun.blackboxa.bean.InstalledAppBean
import top.niunaijun.blackboxa.util.getString
import top.niunaijun.blackbox.fake.frameworks.BResourcesManager
import java.io.File


/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/4/29 23:05
 */

class AppsRepository {
    val TAG: String = "AppsRepository"
    private var mInstalledList = mutableListOf<AppInfo>()

    companion object {
        /** The container is single user, every api call uses user 0. */
        private const val USER_ID = 0
        private const val KEY_APP_SORT = "AppList"
        private const val LEGACY_KEY_APP_SORT = "AppList0"
    }

    /** Reads the saved order, migrating the legacy per-user key once. */
    private fun readSortList(): String? {
        val prefs = AppManager.mRemarkSharedPreferences
        val current = prefs.getString(KEY_APP_SORT, null)
        if (current != null) {
            return current
        }
        val legacy = prefs.getString(LEGACY_KEY_APP_SORT, null)
        if (legacy != null) {
            prefs.edit {
                putString(KEY_APP_SORT, legacy)
                remove(LEGACY_KEY_APP_SORT)
            }
        }
        return legacy
    }

    private fun writeSortList(value: String) {
        AppManager.mRemarkSharedPreferences.edit {
            putString(KEY_APP_SORT, value)
            remove(LEGACY_KEY_APP_SORT)
        }
    }
    
    /**
     * Safely load app label with fallback to package name
     */
    private fun safeLoadAppLabel(applicationInfo: ApplicationInfo): String {
        return try {
            getPackageManager().getApplicationLabel(applicationInfo).toString()
        } catch (e: Exception) {
            Log.w(TAG, "Failed to load label for ${applicationInfo.packageName}: ${e.message}")
            applicationInfo.packageName // Fallback to package name
        }
    }
    
    /**
     * Safely load app icon with fallback to null
     */
    private fun safeLoadAppIcon(applicationInfo: ApplicationInfo): android.graphics.drawable.Drawable? {
        return try {
            getPackageManager().getApplicationIcon(applicationInfo)
        } catch (e: Exception) {
            Log.w(TAG, "Failed to load icon for ${applicationInfo.packageName}: ${e.message}")
            null // Fallback to null icon
        }
    }

    fun previewInstallList() {
        try {
            synchronized(mInstalledList) {
                val installedApplications: List<ApplicationInfo> =
                    getPackageManager().getInstalledApplications(0)
                val installedList = mutableListOf<AppInfo>()

                for (installedApplication in installedApplications) {
                    try {
                        val file = File(installedApplication.sourceDir)

                        if ((installedApplication.flags and ApplicationInfo.FLAG_SYSTEM) != 0) continue

                        if (!AbiUtils.isSupport(file)) continue

                        val isXpModule = BlackBoxCore.get().isXposedModule(file)

                        val info = AppInfo(
                            safeLoadAppLabel(installedApplication),
                            safeLoadAppIcon(installedApplication), // Remove the !! operator to allow null icons
                            installedApplication.packageName,
                            installedApplication.sourceDir,
                            isXpModule
                        )
                        installedList.add(info)
                    } catch (e: Exception) {
                        Log.e(TAG, "Error processing app ${installedApplication.packageName}: ${e.message}")
                    }
                }
                this.mInstalledList.clear()
                this.mInstalledList.addAll(installedList)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in previewInstallList: ${e.message}")
        }
    }

    fun getInstalledAppList(
        loadingLiveData: MutableLiveData<Boolean>,
        appsLiveData: MutableLiveData<List<InstalledAppBean>>
    ) {
        try {
            loadingLiveData.postValue(true)
            synchronized(mInstalledList) {
                val blackBoxCore = BlackBoxCore.get()
                Log.d(TAG, mInstalledList.joinToString(","))
                val newInstalledList = mInstalledList.map {
                    InstalledAppBean(
                        it.name,
                        it.icon, // Remove the !! operator to allow null icons
                        it.packageName,
                        it.sourceDir,
                        blackBoxCore.isInstalled(it.packageName, USER_ID)
                    )
                }
                appsLiveData.postValue(newInstalledList)
                loadingLiveData.postValue(false)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in getInstalledAppList: ${e.message}")
            loadingLiveData.postValue(false)
            appsLiveData.postValue(emptyList())
        }
    }

    fun getInstalledModuleList(
        loadingLiveData: MutableLiveData<Boolean>,
        appsLiveData: MutableLiveData<List<InstalledAppBean>>
    ) {
        try {
            loadingLiveData.postValue(true)
            synchronized(mInstalledList) {
                val blackBoxCore = BlackBoxCore.get()
                val moduleList = mInstalledList.filter {
                    it.isXpModule
                }.map {
                    InstalledAppBean(
                        it.name,
                        it.icon, // Remove the !! operator to allow null icons
                        it.packageName,
                        it.sourceDir,
                        blackBoxCore.isInstalledXposedModule(it.packageName)
                    )
                }
                appsLiveData.postValue(moduleList)
                loadingLiveData.postValue(false)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in getInstalledModuleList: ${e.message}")
            loadingLiveData.postValue(false)
            appsLiveData.postValue(emptyList())
        }
    }

    fun getVmInstallList(appsLiveData: MutableLiveData<List<AppInfo>>) {
        try {
            val blackBoxCore = BlackBoxCore.get()

            val sortList = readSortList()?.split(",")

            val applicationList = blackBoxCore.getInstalledApplications(0, USER_ID)

            // Add null check for applicationList
            if (applicationList == null) {
                Log.e(TAG, "getVmInstallList: applicationList is null")
                appsLiveData.postValue(emptyList())
                return
            }

            Log.d(TAG, "getVmInstallList: applicationList.size=${applicationList.size}")
            if (applicationList.isNotEmpty()) {
                Log.d(TAG, "First app: ${applicationList.first().packageName}")
            } else {
                Log.w(TAG, "getVmInstallList: No applications found")
            }

            val appInfoList = mutableListOf<AppInfo>()
            
            // Sort the application list if sort data exists
            val sortedApplicationList = if (!sortList.isNullOrEmpty()) {
                applicationList.sortedWith(AppsSortComparator(sortList))
            } else {
                applicationList
            }
            
            // Process each application
            sortedApplicationList.forEach { applicationInfo ->
                try {
                    // Add null check for applicationInfo
                    if (applicationInfo == null) {
                        Log.w(TAG, "getVmInstallList: Skipping null applicationInfo")
                        return@forEach
                    }
                    
                    val info = AppInfo(
                        safeLoadAppLabel(applicationInfo),
                        safeLoadAppIcon(applicationInfo), // Remove the !! operator to allow null icons
                        applicationInfo.packageName,
                        applicationInfo.sourceDir,
                        isInstalledXpModule(applicationInfo.packageName)
                    )

                    appInfoList.add(info)
                } catch (e: Exception) {
                    Log.e(TAG, "getVmInstallList: Error processing app ${applicationInfo?.packageName}: ${e.message}")
                }
            }

            Log.d(TAG, "getVmInstallList: processed ${appInfoList.size} apps")
            appsLiveData.postValue(appInfoList)
        } catch (e: Exception) {
            Log.e(TAG, "Error in getVmInstallList: ${e.message}")
            appsLiveData.postValue(emptyList())
        }
    }

    private fun isInstalledXpModule(packageName: String): Boolean {
        return try {
            BlackBoxCore.get().installedXPModules.forEach {
                if (packageName == it.packageName) {
                    return@isInstalledXpModule true
                }
            }
            false
        } catch (e: Exception) {
            Log.e(TAG, "Error checking Xposed module: ${e.message}")
            false
        }
    }

    fun installApk(source: String, resultLiveData: MutableLiveData<String>) {
        try {
            val blackBoxCore = BlackBoxCore.get()
            val installResult = if (URLUtil.isValidUrl(source)) {
                val uri = Uri.parse(source)
                blackBoxCore.installPackageAsUser(uri, USER_ID)
            } else {
                blackBoxCore.installPackageAsUser(source, USER_ID)
            }

            if (installResult.success) {
                updateAppSortList(installResult.packageName, true)
                resultLiveData.postValue(getString(R.string.install_success))
            } else {
                resultLiveData.postValue(getString(R.string.install_fail, installResult.msg))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error installing APK: ${e.message}")
            resultLiveData.postValue("Installation failed: ${e.message}")
        }
    }

    fun unInstall(packageName: String, resultLiveData: MutableLiveData<String>) {
        try {
            // Stop first so a foreground task does not keep a dead proxy activity.
            try {
                BlackBoxCore.get().stopPackage(packageName, USER_ID)
            } catch (e: Exception) {
                Log.w(TAG, "stop before uninstall failed: ${e.message}")
            }
            BlackBoxCore.get().uninstallPackageAsUser(packageName, USER_ID)
            updateAppSortList(packageName, false)
            val context = App.getContext()
            AppProxyStore.remove(context, packageName)
            AppGaidStore.remove(context, packageName)
            AppDeviceStore.remove(context, packageName)
            resultLiveData.postValue(getString(R.string.uninstall_success))
        } catch (e: Exception) {
            Log.e(TAG, "Error uninstalling APK: ${e.message}")
            resultLiveData.postValue("Uninstallation failed: ${e.message}")
        }
    }

    fun launchApk(packageName: String, launchLiveData: MutableLiveData<Boolean>) {
        try {
            val result = BlackBoxCore.get().launchApk(packageName, USER_ID)
            launchLiveData.postValue(result)
        } catch (e: Exception) {
            Log.e(TAG, "Error launching APK: ${e.message}")
            launchLiveData.postValue(false)
        }
    }

    fun clearApkData(packageName: String, resultLiveData: MutableLiveData<String>) {
        try {
            BlackBoxCore.get().clearPackage(packageName, USER_ID)
            resultLiveData.postValue(getString(R.string.clear_success))
        } catch (e: Exception) {
            Log.e(TAG, "Error clearing APK data: ${e.message}")
            resultLiveData.postValue("Clear failed: ${e.message}")
        }
    }

    /**
     * 更新排序列表
     * @param pkg String
     * @param isAdd Boolean true是添加，false是移除
     */
    private fun updateAppSortList(pkg: String, isAdd: Boolean) {
        try {
            val savedSortList = readSortList().orEmpty()

            val sortList = linkedSetOf<String>()
            sortList.addAll(savedSortList.split(",").filter { it.isNotEmpty() })

            if (isAdd) {
                sortList.add(pkg)
            } else {
                sortList.remove(pkg)
            }

            writeSortList(sortList.joinToString(","))
        } catch (e: Exception) {
            Log.e(TAG, "Error updating app sort list: ${e.message}")
        }
    }

    /**
     * 保存排序后的apk顺序
     */
    fun updateApkOrder(dataList: List<AppInfo>) {
        try {
            writeSortList(dataList.joinToString(",") { it.packageName })
        } catch (e: Exception) {
            Log.e(TAG, "Error updating APK order: ${e.message}")
        }
    }
}
