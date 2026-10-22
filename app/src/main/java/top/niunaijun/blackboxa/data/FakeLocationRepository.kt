package top.niunaijun.blackboxa.data

import android.content.pm.ApplicationInfo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackbox.entity.location.BLocation
import top.niunaijun.blackbox.fake.frameworks.BLocationManager
import top.niunaijun.blackboxa.bean.FakeLocationBean

/**
 * getInstalledApplications and query fake location of each of them.
 * mode and location configuration are respectively concept.
 * Location config just store the location but mode decides whether turns on it
 * each application has three pattern.Global: use global fake location,
 * self: use own config, close: use real config
 * @Description:
 * @Author: BlackBoxing
 * @CreateDate: 2022/3/12 21:14
 */
class FakeLocationRepository {
    val TAG: String = "FakeLocationRepository"

    companion object {
        /** The container is single user, every api call uses user 0. */
        private const val USER_ID = 0
    }

    fun setPattern(pkg: String, pattern: Int) {
        BLocationManager.get().setPattern(USER_ID, pkg, pattern)
    }

    private fun getPattern(pkg: String): Int {
        return BLocationManager.get().getPattern(USER_ID, pkg)
    }

    private fun getLocation(pkg: String): BLocation? {
        return BLocationManager.get().getLocation(USER_ID, pkg)
    }

    fun setLocation(pkg: String, location: BLocation) {
        BLocationManager.get().setLocation(USER_ID, pkg, location)
    }

    fun getInstalledAppList(
        appsFakeLiveData: MutableLiveData<List<FakeLocationBean>>
    ) {
        val installedList = mutableListOf<FakeLocationBean>()
        val installedApplications: List<ApplicationInfo> =
            BlackBoxCore.get().getInstalledApplications(0, USER_ID)
        // List<ApplicationInfo> -> List<FakeLocationBean>
        for (installedApplication in installedApplications) {
            val info = FakeLocationBean(
                installedApplication.loadLabel(BlackBoxCore.getPackageManager()).toString(),
                installedApplication.loadIcon(BlackBoxCore.getPackageManager()),
                installedApplication.packageName,
                getPattern(installedApplication.packageName),
                getLocation(installedApplication.packageName)
            )
            installedList.add(info)
        }

        Log.d(TAG, installedList.joinToString(","))
        appsFakeLiveData.postValue(installedList)
    }
}
