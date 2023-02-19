package com.selsela.composebaseproject.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selsela.composebaseproject.data.remote.categories.repository.CategoryRepository
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.ui.screens.categories.state.CategoryUiState
import com.selsela.composebaseproject.util.networking.model.ErrorsData
import com.selsela.composebaseproject.util.networking.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    var isLoaded = false

    /**
     * State Flows
     */
    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()
    private var state: CategoryUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }


    /**
     * API Requests
     */
    fun getCategories() {
        viewModelScope.launch {
            state = state.copy(
                state = State.LOADING
            )
            repository.getCategories()
                .collect { result ->
                    val categoriesUiState = when (result.status) {
                        Status.SUCCESS -> {
                            isLoaded = true
                            CategoryUiState(
                                state = State.SUCCESS,
                                categories = result.data
                            )
                        }

                        Status.LOADING ->
                            CategoryUiState(
                                state = State.LOADING
                            )

                        Status.ERROR -> CategoryUiState(
                            state = State.ERROR,
                            error = ErrorsData(
                                result.errors,
                                result.message,
                                result.statusCode
                            )
                        )
                    }
                    state = categoriesUiState
                }
        }
    }


}