package tare.app.test.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tare.app.test.api.services.NetworkService
import tare.app.test.api.utils.Constants.BASE_URL


class ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(NetworkService::class.java)
}