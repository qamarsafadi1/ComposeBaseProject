package com.selsela.composebaseproject.data.local

import android.content.Context
import android.content.SharedPreferences
import com.selsela.composebaseproject.data.remote.config.model.cities.City
import com.selsela.composebaseproject.data.remote.config.model.config.Configurations
import com.selsela.composebaseproject.data.remote.config.model.pages.Page
import com.selsela.composebaseproject.data.remote.config.model.payment.Payment
import com.selsela.composebaseproject.util.fromJson
import com.selsela.composebaseproject.util.toJson

object PreferenceHelper {

    private const val APP_LOCALE = "APP_LOCALE"
    private const val FCM_TOKEN = "fcm_token"
    private const val CONFIGURATIONS = "configurations"
    private const val PAYMENTS = "payments"
    private const val CITIES = "cities"
    private const val CONDITIONS = "conditions"
    private const val PRIVACY = "privacy"
    private const val ABOUT_APP = "about_app"

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.appLocale
        get() = getString(APP_LOCALE, "ar")
        set(value) {
            editMe {
                it.putString(APP_LOCALE, value)
            }
        }

    var SharedPreferences.configurations
        get() = getString(CONFIGURATIONS, null)?.fromJson<Configurations>()
        set(value) {
            editMe {
                it.putString(CONFIGURATIONS, value?.toJson())
            }
        }

    var SharedPreferences.conditions
        get() = getString(CONDITIONS, null)?.fromJson<Page>()
        set(value) {
            editMe {
                it.putString(CONDITIONS, value?.toJson())
            }
        }

    var SharedPreferences.privacy
        get() = getString(PRIVACY, null)?.fromJson<Page>()
        set(value) {
            editMe {
                it.putString(PRIVACY, value?.toJson())
            }
        }
    var SharedPreferences.aboutApp
        get() = getString(ABOUT_APP, null)?.fromJson<Page>()
        set(value) {
            editMe {
                it.putString(ABOUT_APP, value?.toJson())
            }
        }

    var SharedPreferences.payments
        get() = getString(PAYMENTS, null)?.fromJson<List<Payment>>()
        set(value) {
            editMe {
                it.putString(PAYMENTS, value?.toJson())
            }
        }

    var SharedPreferences.cities
        get() = getString(CITIES, null)?.fromJson<List<City>>()
        set(value) {
            editMe {
                it.putString(CITIES, value?.toJson())
            }
        }

    var SharedPreferences.fcmToken
        get() = getString(FCM_TOKEN, "")
        set(value) {
            editMe {
                it.putString(FCM_TOKEN, value)
            }
        }


    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}