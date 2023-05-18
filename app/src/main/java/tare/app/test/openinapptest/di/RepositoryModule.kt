package tare.app.test.openinapptest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tare.app.test.api.ApiClient
import tare.app.test.openinapptest.ui.dashboard.DashboardRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideApiClient(): ApiClient {
        return ApiClient()
    }

    @Singleton
    @Provides
    fun provideDashboardRepository(apiClient: ApiClient): DashboardRepository {
        return DashboardRepository(apiClient)
    }
}