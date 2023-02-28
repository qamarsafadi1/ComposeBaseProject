package com.selsela.composebaseproject.ui.screens.auth

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.data.local.PreferenceHelper.user
import com.selsela.composebaseproject.data.remote.auth.repository.AuthRepository
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.ui.screens.auth.state.AuthUiState
import com.selsela.composebaseproject.ui.theme.Red
import com.selsela.composebaseproject.util.Constants.NOT_VERIFIED
import com.selsela.composebaseproject.util.InputWrapper
import com.selsela.composebaseproject.util.networking.model.ErrorsData
import com.selsela.composebaseproject.util.networking.model.Status
import com.selsela.composebaseproject.util.validatePassword
import com.selsela.composebaseproject.util.validatePhone
import com.selsela.composebaseproject.util.validateRequired
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


const val MOBILE = "mobile"
const val PASSWORD = "password"
const val CODE = "code"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AuthRepository,
    preferences: SharedPreferences,
    private val application: Application
) : ViewModel() {

    /**
     * Form fields for validation
     */

    val mobile = savedStateHandle.getStateFlow(MOBILE, InputWrapper(inputValue = if (preferences.user != null) preferences.user?.mobile ?: "567777010" else "567777010"))
    val code = savedStateHandle.getStateFlow(CODE, InputWrapper())
    val password = savedStateHandle.getStateFlow(PASSWORD, InputWrapper())

    /**
     * State Flows
     */
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()
    private var authState: AuthUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    /**
     * Combine form fields to detect if all inputs are valid or not
     */

    val areInputsValid = combine(mobile, password) { mobile, password ->
        mobile.inputValue.isNotEmpty() && mobile.validationMessage.isNullOrEmpty() && password.inputValue.isNotEmpty() && password.validationMessage.isNullOrEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    val isCodeValid = combine(mobile,code) { mobile, code ->
        code.inputValue.isNotEmpty() && code.validationMessage.isNullOrEmpty() && code.inputValue.length == 4  && mobile.inputValue.isNotEmpty() && mobile.validationMessage.isNullOrEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    /**
     * Validate mobile field
     */
    fun onMobileEntered(input: String) {
        if (input.isEmpty().not()) {
            val errorId = input.validatePhone(application.applicationContext)
            savedStateHandle[MOBILE] = mobile.value.copy(
                inputValue = input, validationMessage = errorId,
                borderColor = if (errorId.isNotEmpty()) Red else Color.Black
            )
        } else {
            savedStateHandle[MOBILE] = mobile.value.copy(
                inputValue = input, validationMessage = null,
                borderColor = Color.Black
            )
        }
    }

    /**
     * Validate password field
     */

    fun onPasswordEntered(input: String) {
        if (input.isEmpty().not()) {
            val errorId = input.validatePassword(application.applicationContext)
            savedStateHandle[PASSWORD] = password.value.copy(
                inputValue = input, validationMessage = errorId,
                borderColor = if (errorId.isNotEmpty()) Red else Color.Black
            )
        } else {
            savedStateHandle[PASSWORD] = password.value.copy(
                inputValue = input, validationMessage = null,
                borderColor = Color.Black
            )
        }
    }

    /**
     * Validate code field
     */

    fun onCodeEntered(input: String, isOtpFilled: Boolean) {
        if (input.isEmpty().not() || isOtpFilled.not()) {
            val errorId = input.validateRequired(
                application.applicationContext, application.applicationContext.getString(
                    R.string.verify_code
                )
            )
            savedStateHandle[CODE] = code.value.copy(
                inputValue = input, validationMessage = errorId,
                borderColor = if (errorId.isNotEmpty()) Red else Color.Black
            )
        } else {
            savedStateHandle[CODE] = code.value.copy(
                inputValue = input, validationMessage = null,
                borderColor = Color.Black
            )
        }
    }


    /**
     * API Requests
     */

    fun login() {
        viewModelScope.launch {
            authState = authState.copy(
                state = State.LOADING
            )
            repository.auth(mobile.value.inputValue, password.value.inputValue)
                .collect { result ->
                    val authUiState = when (result.status) {
                        Status.SUCCESS -> {
                            if (result.data?.status == NOT_VERIFIED) {
                                AuthUiState(
                                    state = State.SUCCESS,
                                    onVerify = triggered(result.data)
                                )
                            } else {
                                AuthUiState(
                                    state = State.SUCCESS,
                                    onSuccess = triggered(result.message ?: ""),
                                )
                            }
                        }

                        Status.LOADING ->
                            AuthUiState(
                                state = State.LOADING,
                            )

                        Status.ERROR -> AuthUiState(
                            state = State.ERROR,
                            onFailure = triggered(
                                ErrorsData(
                                    result.errors,
                                    result.message,
                                    result.statusCode
                                )
                            )
                        )
                    }
                    authState = authUiState
                }
        }
    }
    fun verifyCode() {
        viewModelScope.launch {
            authState = authState.copy(
                state = State.LOADING
            )
            repository.verifyCode(mobile.value.inputValue, code.value.inputValue)
                .collect { result ->
                    val authUiState = when (result.status) {
                        Status.SUCCESS -> {
                                AuthUiState(
                                    state = State.SUCCESS,
                                    onSuccess = triggered(result.message ?: ""),
                                )
                        }

                        Status.LOADING ->
                            AuthUiState(
                                state = State.LOADING,
                            )

                        Status.ERROR -> AuthUiState(
                            state = State.ERROR,
                            onFailure = triggered(
                                ErrorsData(
                                    result.errors,
                                    result.message,
                                    result.statusCode
                                )
                            )
                        )
                    }
                    authState = authUiState
                }
        }
    }


    /**
     * Reset handlers when single event
     */

    fun onSuccess() {
        authState = authState.copy(onSuccess = consumed())
    }

    fun onVerify() {
        authState = authState.copy(onVerify = consumed())
    }

    fun onFailure() {
        authState = authState.copy(onFailure = consumed())
    }
}

