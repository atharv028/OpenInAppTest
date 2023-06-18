package tare.app.test.openinapptest.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tare.app.test.api.models.Link
import tare.app.test.openinapptest.utils.ApiResponse
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    sealed class State {
        data class Loaded(
            val todayClicks: String,
            val topLocation: String,
            val topSource: String,
            val chartData: List<Entry>,
            val chartLabels: List<String>,
            val topLinks: List<Link>,
            val recentLinks: List<Link>
        ) : State()

        data class Error(
            val message: String
        ) : State()

        object Loading : State()

    }

    private val _dashboardData = MutableLiveData<State>(State.Loading)
    var dashboardData = _dashboardData
        private set

    val selectedLink = MutableLiveData(LinkType.TOP)

    init {
        getDashboardData()
    }

    fun getDashboardData() {
        _dashboardData.postValue(State.Loading)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                when (val response = repository.getDashboard()) {
                    is ApiResponse.Success -> {
                        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val startDate = formatter.parse(response.data.data?.chartData?.keys?.firstOrNull() ?: "2021-05-15")
                        val data = mutableListOf<Entry>()
                        val labels = mutableListOf<String>()
                        var count = 0f
                        response.data.data?.chartData?.values?.forEach {
                            data.add(Entry(count++, it?.toFloat() ?: 0f))
                        }
                        val start = Calendar.getInstance()
                        start.time = startDate!!
                        for (i in 0..count.toInt()) {
                            labels.add(formatter.format(start.time))
                            start.add(Calendar.DAY_OF_MONTH, 1)
                        }
                        _dashboardData.postValue(
                            State.Loaded(
                                todayClicks = response.data.todayClicks?.toString() ?: "N/A",
                                topLocation = if (response.data.topLocation.isNullOrEmpty()) {
                                    "N/A"
                                } else {
                                    response.data.topLocation!!
                                },
                                topSource = if (response.data.topSource.isNullOrEmpty()) {
                                    "N/A"
                                } else {
                                    response.data.topSource!!
                                },
                                chartData = data,
                                chartLabels = labels,
                                topLinks = response.data.data?.topLinks ?: listOf(),
                                recentLinks = response.data.data?.recentLinks ?: listOf()
                            )
                        )
                    }

                    is ApiResponse.Error -> {
                        _dashboardData.postValue(State.Error(response.error))
                    }
                }
            } catch (e: Exception) {
                _dashboardData.postValue(State.Error(e.message.toString()))
            }
        }
    }
}

enum class LinkType {
    TOP,
    RECENT
}