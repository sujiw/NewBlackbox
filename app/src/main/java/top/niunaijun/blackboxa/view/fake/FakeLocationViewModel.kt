package top.niunaijun.blackboxa.view.fake

import androidx.lifecycle.MutableLiveData
import top.niunaijun.blackbox.entity.location.BLocation
import top.niunaijun.blackboxa.bean.FakeLocationBean
import top.niunaijun.blackboxa.data.FakeLocationRepository
import top.niunaijun.blackboxa.view.base.BaseViewModel

/**
 *
 * @Author: BlackBoxing
 * @CreateDate: 2022/3/14
 */
class FakeLocationViewModel(private val mRepo: FakeLocationRepository) : BaseViewModel() {

    val appsLiveData = MutableLiveData<List<FakeLocationBean>>()


    fun getInstallAppList() {
        launchOnUI {
            mRepo.getInstalledAppList(appsLiveData)
        }
    }

    fun setPattern(pkg: String, pattern: Int) {
        launchOnUI {
            mRepo.setPattern(pkg, pattern)
        }
    }

    fun setLocation(pkg: String, location: BLocation) {
        launchOnUI {
            mRepo.setLocation(pkg, location)
        }
    }

}
