package com.selsela.composebaseproject.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selsela.composebaseproject.BaseApp.Companion.LocalData
import com.selsela.composebaseproject.data.local.PreferenceHelper.configurations
import com.selsela.composebaseproject.data.remote.config.model.config.Configurations
import com.selsela.composebaseproject.data.remote.config.repository.ConfigurationsRepository
import com.selsela.composebaseproject.util.networking.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val repository: ConfigurationsRepository,
) : ViewModel() {

    init {
        getConfig()
    }

    private fun getConfig() {
        viewModelScope.launch {
            repository.getConfigurations()
//            repository.getPaymentsType()
//            repository.getCities()
//            repository.getConditions()
//            repository.getPrivacy()
//            repository.getAboutApp()
        }
    }
}