package tare.app.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import tare.app.test.api.ApiClient

@OptIn(ExperimentalCoroutinesApi::class)
class ApiTest {

    private val apiClient = ApiClient()

    @Test
    fun `Test Dashboard Api`() = runTest {
        val data = apiClient.api.getDashboard("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
        println(data.body())
        assert(data.body() != null)
    }
}