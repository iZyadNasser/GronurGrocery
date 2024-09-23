package com.example.gronurgrocery.features.profile.presentation.profile_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronurgrocery.features.profile.domain.use_case.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileMainViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun onLogoutClick() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}