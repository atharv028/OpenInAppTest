package tare.app.test.openinapptest.ui.dashboard

import tare.app.test.api.ApiClient
import tare.app.test.api.models.ResponseDashboard
import tare.app.test.openinapptest.BuildConfig
import tare.app.test.openinapptest.utils.ApiResponse
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun getDashboard(): ApiResponse<ResponseDashboard> {
        val resp = apiClient.api.getDashboard("Bearer ${BuildConfig.TOKEN_KEY}")
        return when (resp.isSuccessful) {
            true -> ApiResponse.Success(resp.body()!!)
            false -> ApiResponse.Error(resp.message())
        }
    }
}