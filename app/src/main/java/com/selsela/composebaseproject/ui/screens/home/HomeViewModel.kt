package com.selsela.composebaseproject.ui.screens.home

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.selsela.composebaseproject.data.local.PreferenceHelper.accessToken
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val preferences: SharedPreferences
) : ViewModel() {
    val userLoggedIn = mutableStateOf(preferences.accessToken.isNullOrEmpty().not())

}