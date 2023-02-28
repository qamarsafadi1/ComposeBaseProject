package com.selsela.composebaseproject.di

import android.content.SharedPreferences
import com.selsela.composebaseproject.data.remote.auth.repository.AuthRepository
import com.selsela.composebaseproject.data.remote.auth.service.AuthService
import com.selsela.composebaseproject.data.remote.categories.repository.CategoryRepository
import com.selsela.composebaseproject.data.remote.categories.service.CategoryService
import com.selsela.composebaseproject.data.remote.config.repository.ConfigurationsRepository
import com.selsela.composebaseproject.data.remote.config.service.ConfigServiceApi
import com.selsela.composebaseproject.data.remote.orders.repository.OrderRepository
import com.selsela.composebaseproject.data.remote.orders.service.OrderService
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
        return ConfigurationsRepository(apiService, preferences)
    }

    @Provides
    @Singleton
    fun providerAuthRepository(
        apiService: AuthService,
        preferences: SharedPreferences
    ): AuthRepository {
        return AuthRepository(apiService, preferences)
    }

    @Provides
    @Singleton
    fun providerCategoryRepository(
        apiService: CategoryService,
    ): CategoryRepository {
        return CategoryRepository(apiService)
    }

    @Provides
    @Singleton
    fun providerOrderRepository(
        apiService: OrderService,
    ): OrderRepository {
        return OrderRepository(apiService)
    }
}