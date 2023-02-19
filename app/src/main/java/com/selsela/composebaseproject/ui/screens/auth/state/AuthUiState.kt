package com.selsela.composebaseproject.ui.screens.auth.state

import androidx.annotation.Keep
import com.selsela.composebaseproject.data.remote.auth.model.User
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.util.networking.model.ErrorsData
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

@Keep
data class AuthUiState(
    val state: State = State.IDLE,
    val onSuccess: StateEventWithContent<String> = consumed(),
    val onVerify: StateEventWithContent<User> = consumed(),
    val onFailure: StateEventWithContent<ErrorsData> = consumed(),
)