package com.selsela.composebaseproject.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selsela.composebaseproject.data.remote.categories.model.Service
import com.selsela.composebaseproject.data.remote.categories.repository.CategoryRepository
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.ui.core.state.UiState
import com.selsela.composebaseproject.util.networking.model.ErrorsData
import com.selsela.composebaseproject.util.networking.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale.Category
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    var isLoaded = false

    /**
     * State Flows
     */
    private val _uiState = MutableStateFlow(UiState<Service>())
    val uiState: StateFlow<UiState<Service>> = _uiState.asStateFlow()
    private var state: UiState<Service>
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
                            UiState<Service>(
                                state = State.SUCCESS,
                                dataList = result.data
                            )
                        }

                        Status.LOADING ->
                            UiState<Service>(
                                state = State.LOADING
                            )

                        Status.ERROR -> UiState<Service>(
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

    fun getCategoriesDetails(index: Int) {
        val categoriesUiState =
            UiState<Service>(
                state = State.SUCCESS,
                data = state.dataList?.get(index),
                dataList = state.dataList
            )
        state = categoriesUiState
    }


}