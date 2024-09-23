package com.example.gronurgrocery.common.presentation.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronurgrocery.common.domain.use_cases.ReadOnboardingStateUseCase
import com.example.gronurgrocery.common.domain.use_cases.ReadUserTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainAppViewModel @Inject constructor(
    private val readOnboardingStateUseCase: ReadOnboardingStateUseCase,
    private val readUserTokenUseCase: ReadUserTokenUseCase
) : ViewModel() {

    private var _onboardingState: MutableState<Boolean> = mutableStateOf(false)
    val onboardingState : State<Boolean> = _onboardingState

    private var _userTokenState: MutableState<String> = mutableStateOf("")
    val userTokenState: State<String> = _userTokenState

    init {
        getOnboardingState()
        getUserTokenState()
    }

    private fun getOnboardingState() {
        viewModelScope.launch {
            readOnboardingStateUseCase().collect { state ->
                _onboardingState.value = state
            }
        }
    }

    private fun getUserTokenState() {
        viewModelScope.launch {
            readUserTokenUseCase().collect { state ->
                _userTokenState.value = state ?: ""
            }
        }
    }
}