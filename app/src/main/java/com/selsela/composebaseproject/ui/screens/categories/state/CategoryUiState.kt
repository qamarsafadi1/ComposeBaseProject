package com.selsela.composebaseproject.ui.screens.categories.state

import androidx.annotation.Keep
import com.selsela.composebaseproject.data.remote.categories.model.Service
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.util.networking.model.ErrorsData

@Keep
data class CategoryUiState(
    val state: State = State.IDLE,
    val categories: List<Service>? = null,
    val error: ErrorsData? = null,

)