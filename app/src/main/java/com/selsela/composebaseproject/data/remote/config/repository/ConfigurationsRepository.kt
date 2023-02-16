package com.selsela.composebaseproject.data.remote.config.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.selsela.composebaseproject.data.local.PreferenceHelper.aboutApp
import com.selsela.composebaseproject.data.local.PreferenceHelper.cities
import com.selsela.composebaseproject.data.local.PreferenceHelper.conditions
import com.selsela.composebaseproject.data.local.PreferenceHelper.configurations
import com.selsela.composebaseproject.data.local.PreferenceHelper.payments
import com.selsela.composebaseproject.data.local.PreferenceHelper.privacy
import com.selsela.composebaseproject.data.remote.config.model.cities.City
import com.selsela.composebaseproject.data.remote.config.model.config.Configurations
import com.selsela.composebaseproject.data.remote.config.model.pages.Page
import com.selsela.composebaseproject.data.remote.config.model.payment.Payment
import com.selsela.composebaseproject.data.remote.config.service.ConfigServiceApi
import com.selsela.composebaseproject.util.handleExceptions
import com.selsela.composebaseproject.util.handleSuccess
import com.selsela.composebaseproject.util.networking.model.ErrorBase
import com.selsela.composebaseproject.util.networking.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConfigurationsRepository @Inject constructor(
    private val api: ConfigServiceApi,
    private val preferences: SharedPreferences

) {

    suspend fun getConfigurations(): Flow<Resource<Configurations>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<Configurations>> = try {
            val response = api.getConfigurations()
            if (response.isSuccessful) {
                preferences.configurations = response.body()?.configurations
                handleSuccess(
                    response.body()?.configurations,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                val gson = Gson()
                val errorBase = gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                errorBase.statusCode = response.code()
                handleExceptions(errorBase)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }

    suspend fun getPaymentsType(): Flow<Resource<List<Payment>>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<List<Payment>>> = try {
            val response = api.getPaymentsType()
            if (response.isSuccessful) {
                preferences.payments = response.body()?.payments
                handleSuccess(
                    response.body()?.payments,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                val gson = Gson()
                val errorBase = gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                errorBase.statusCode = response.code()
                handleExceptions(errorBase)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }

    suspend fun getCities(countryId: Int = 1 ): Flow<Resource<List<City>>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<List<City>>> = try {
            val response = api.getCities(countryId)
            if (response.isSuccessful) {
                preferences.cities = response.body()?.cities
                handleSuccess(
                    response.body()?.cities,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                val gson = Gson()
                val errorBase = gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                errorBase.statusCode = response.code()
                handleExceptions(errorBase)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }

    suspend fun getConditions(): Flow<Resource<Page>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<Page>> = try {
            val response = api.getPages(1)
            if (response.isSuccessful) {
                preferences.conditions = response.body()?.page
                handleSuccess(
                    response.body()?.page,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                val gson = Gson()
                val errorBase = gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                errorBase.statusCode = response.code()
                handleExceptions(errorBase)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }

    suspend fun getPrivacy(): Flow<Resource<Page>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<Page>> = try {
            val response = api.getPages(2)
            if (response.isSuccessful) {
                preferences.privacy = response.body()?.page
                handleSuccess(
                    response.body()?.page,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                val gson = Gson()
                val errorBase = gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                errorBase.statusCode = response.code()
                handleExceptions(errorBase)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }

    suspend fun getAboutApp(): Flow<Resource<Page>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<Page>> = try {
            val response = api.getPages(3)
            if (response.isSuccessful) {
                preferences.aboutApp = response.body()?.page
                handleSuccess(
                    response.body()?.page,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                val gson = Gson()
                val errorBase = gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                errorBase.statusCode = response.code()
                handleExceptions(errorBase)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }


}