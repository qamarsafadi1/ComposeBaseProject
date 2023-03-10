package com.selsela.composebaseproject.di

import android.app.Application
import android.content.SharedPreferences
import com.selsela.composebaseproject.data.local.PreferenceHelper
import com.selsela.composebaseproject.data.remote.auth.service.AuthService
import com.selsela.composebaseproject.data.remote.categories.service.CategoryService
import com.selsela.composebaseproject.data.remote.config.service.ConfigServiceApi
import com.selsela.composebaseproject.data.remote.orders.service.OrderService
import com.selsela.composebaseproject.util.networking.HeaderInterceptor
import com.selsela.composebaseproject.util.networking.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitBuilder: RetrofitBuilder,
        headerInterceptor: HeaderInterceptor
    ): Retrofit =
        retrofitBuilder
            .addInterceptors(headerInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideConfigApi(retrofit: Retrofit): ConfigServiceApi =
        retrofit.create(ConfigServiceApi::class.java)

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): CategoryService = retrofit.create(CategoryService::class.java)

    @Provides
    @Singleton
    fun provideOrderApi(retrofit: Retrofit): OrderService = retrofit.create(OrderService::class.java)


    @Provides
    @Singleton
    fun provideLocalPreference(application: Application): SharedPreferences =
        PreferenceHelper.customPreference(application.applicationContext, "APP_PREFERENCE")

}