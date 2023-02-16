package com.selsela.composebaseproject.di

import android.content.SharedPreferences
import com.selsela.composebaseproject.data.remote.config.repository.ConfigurationsRepository
import com.selsela.composebaseproject.data.remote.config.service.ConfigServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providerConfigurationsRepository(
        apiService: ConfigServiceApi,
        preferences: SharedPreferences
    ): ConfigurationsRepository {
        return ConfigurationsRepository(apiService,preferences)
    }
}