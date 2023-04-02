package com.selsela.composebaseproject.data.remote.auth.repository

import android.content.SharedPreferences
import androidx.annotation.Keep
import com.google.gson.Gson
import com.selsela.composebaseproject.data.local.PreferenceHelper.aboutApp
import com.selsela.composebaseproject.data.local.PreferenceHelper.accessToken
import com.selsela.composebaseproject.data.local.PreferenceHelper.user
import com.selsela.composebaseproject.data.remote.auth.model.AuthResponse
import com.selsela.composebaseproject.data.remote.auth.model.User
import com.selsela.composebaseproject.data.remote.auth.service.AuthService
import com.selsela.composebaseproject.util.Constants.NOT_VERIFIED
import com.selsela.composebaseproject.util.handleExceptions
import com.selsela.composebaseproject.util.handleSuccess
import com.selsela.composebaseproject.util.networking.model.ErrorBase
import com.selsela.composebaseproject.util.networking.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Keep
class AuthRepository @Inject constructor(
    private val api: AuthService,
    private val preferences: SharedPreferences
) {

    suspend fun auth(
        mobile: String,
        password: String,
    ): Flow<Resource<User>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<User>> = try {
            val body = HashMap<String, Any>()
            body["mobile"] = mobile
            //   body["password"] = password you can use password if your request ask you to.
            body["country_id"] = "1"
            val response = api.auth(body)
            if (response.isSuccessful) {
                User.CurrentUser = response.body()?.user
                if (response.body()?.user?.status != NOT_VERIFIED)
                    User.AccessToken = response.body()?.user?.accessToken
                handleSuccess(
                    response.body()?.user,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                if (response.code() == 451) {
                    val responseUser: AuthResponse? = Gson().fromJson(
                        response.errorBody()?.charStream(),
                        AuthResponse::class.java
                    )
                    User.CurrentUser = responseUser?.user
                    handleSuccess(
                        response.body()?.user,
                        response.body()?.responseMessage ?: response.message()
                    )
                } else {
                    val gson = Gson()
                    val errorBase =
                        gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                    errorBase.statusCode = response.code()
                    handleExceptions(errorBase)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }

    suspend fun verifyCode(
        mobile: String,
        code: String,
    ): Flow<Resource<User>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<User>> = try {
            val body = HashMap<String, Any>()
            body["mobile"] = mobile
            body["code"] = code
            body["country_id"] = "1"
            val response = api.verifyCode(body)
            if (response.isSuccessful) {
                User.CurrentUser = response.body()?.user
                User.AccessToken = response.body()?.user?.accessToken
                handleSuccess(
                    response.body()?.user,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                if (response.code() == 451) {
                    val responseUser: AuthResponse? = Gson().fromJson(
                        response.errorBody()?.charStream(),
                        AuthResponse::class.java
                    )
                    User.CurrentUser = responseUser?.user
                    handleSuccess(
                        response.body()?.user,
                        response.body()?.responseMessage ?: response.message()
                    )
                } else {
                    val gson = Gson()
                    val errorBase =
                        gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                    errorBase.statusCode = response.code()
                    handleExceptions(errorBase)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }

}