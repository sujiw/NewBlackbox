package top.niunaijun.blackboxa.data

import androidx.lifecycle.MutableLiveData
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.bean.GmsBean
import top.niunaijun.blackboxa.bean.GmsInstallBean
import top.niunaijun.blackboxa.util.getString

/**
 *
 * @Description:
 * @Author: BlackBox
 * @CreateDate: 2022/3/2 21:14
 */
class GmsRepository {

    companion object {
        /** The container is single user, every api call uses user 0. */
        private const val USER_ID = 0
    }

    fun getGmsInstalledList(mInstalledLiveData: MutableLiveData<List<GmsBean>>) {
        val isInstalled = BlackBoxCore.get().isInstallGms(USER_ID)
        mInstalledLiveData.postValue(listOf(GmsBean(isInstalled)))
    }

    fun installGms(mUpdateInstalledLiveData: MutableLiveData<GmsInstallBean>) {
        val installResult = BlackBoxCore.get().installGms(USER_ID)

        val result = if (installResult.success) {
            getString(R.string.install_success)
        } else {
            getString(R.string.install_fail, installResult.msg)
        }

        mUpdateInstalledLiveData.postValue(GmsInstallBean(installResult.success, result))
    }

    fun uninstallGms(mUpdateInstalledLiveData: MutableLiveData<GmsInstallBean>) {
        var isSuccess = false
        if (BlackBoxCore.get().isInstallGms(USER_ID)) {
            isSuccess = BlackBoxCore.get().uninstallGms(USER_ID)
        }

        val result = if (isSuccess) {
            getString(R.string.uninstall_success)
        } else {
            getString(R.string.uninstall_fail)
        }

        mUpdateInstalledLiveData.postValue(GmsInstallBean(isSuccess, result))
    }
}
