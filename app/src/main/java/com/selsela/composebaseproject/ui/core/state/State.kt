package com.selsela.composebaseproject.ui.core.state

import androidx.annotation.Keep
import com.selsela.composebaseproject.data.remote.auth.model.User
import com.selsela.composebaseproject.util.networking.model.ErrorsData
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

enum class State {
    LOADING, SUCCESS, ERROR, IDLE, PAGINATING
}

@Keep
data class UiState<E>(
    val state: State = State.IDLE,
    val onSuccess: StateEventWithContent<E?> = consumed(),
    val onSuccessMessage: StateEventWithContent<String?> = consumed(),
    val onVerify: StateEventWithContent<User> = consumed(),
    val onFailure: StateEventWithContent<ErrorsData?> = consumed(),
    val dataList: List<E>? = null,
    val data: E? = null,
    val error: ErrorsData? = null,
)