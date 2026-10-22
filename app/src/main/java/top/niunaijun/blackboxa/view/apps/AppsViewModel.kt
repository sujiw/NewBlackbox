package top.niunaijun.blackboxa.view.apps

import androidx.lifecycle.MutableLiveData
import top.niunaijun.blackboxa.bean.AppInfo
import top.niunaijun.blackboxa.data.AppsRepository
import top.niunaijun.blackboxa.view.base.BaseViewModel

/**
 *
 * @Description: 软件显示界面 viewModel
 * @Author: wukaicheng
 * @CreateDate: 2021/4/29 22:36
 */
class AppsViewModel(private val repo: AppsRepository) : BaseViewModel() {

    val appsLiveData = MutableLiveData<List<AppInfo>>()

    val resultLiveData = MutableLiveData<String>()

    val launchLiveData = MutableLiveData<Boolean>()

    //利用LiveData只更新最后一次的特性，用来保存app顺序
    val updateSortLiveData = MutableLiveData<Boolean>()

    fun getInstalledApps() {
        launchOnUI {
            repo.getVmInstallList(appsLiveData)
        }
    }

    fun install(source: String) {
        launchOnUI {
            repo.installApk(source, resultLiveData)
        }
    }

    fun unInstall(packageName: String) {
        launchOnUI {
            repo.unInstall(packageName, resultLiveData)
        }
    }

    fun clearApkData(packageName: String) {
        launchOnUI {
            repo.clearApkData(packageName, resultLiveData)
        }
    }

    fun launchApk(packageName: String) {
        launchOnUI {
            repo.launchApk(packageName, launchLiveData)
        }
    }

    fun updateApkOrder(dataList: List<AppInfo>) {
        launchOnUI {
            repo.updateApkOrder(dataList)
        }
    }
}
