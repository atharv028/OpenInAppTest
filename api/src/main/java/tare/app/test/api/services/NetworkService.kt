package tare.app.test.api.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import tare.app.test.api.models.ResponseDashboard
import tare.app.test.api.utils.Constants.DASHBOARD_ENDPOINT

interface NetworkService {

    @GET(DASHBOARD_ENDPOINT)
    suspend fun getDashboard(
        @Header("Authorization") authToken: String,
    ): Response<ResponseDashboard>

}