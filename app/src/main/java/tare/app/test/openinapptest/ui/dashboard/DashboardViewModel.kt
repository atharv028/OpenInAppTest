package tare.app.test.openinapptest.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tare.app.test.api.models.ResponseDashboard
import tare.app.test.openinapptest.utils.ApiResponse
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _dashboardData = MutableLiveData<ApiResponse<ResponseDashboard>>()
    val dashboardData
        get() = _dashboardData

    val selectedLink = MutableLiveData(LinkType.TOP)

    init {
        getDashboardData()
    }

    fun getDashboardData() {
        _dashboardData.postValue(ApiResponse.Loading)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getDashboard()
                _dashboardData.postValue(response)
            } catch (e: Exception) {
                _dashboardData.postValue(ApiResponse.Error(e.message.toString()))
            }
        }
    }
}

enum class LinkType {
    TOP,
    RECENT
}