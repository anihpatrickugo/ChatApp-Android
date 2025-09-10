package com.example.test_android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.local.TokenManager
import kotlinx.coroutines.launch

class UserViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {

    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            tokenManager.clearTokens()
            onLoggedOut() // notify UI (e.g., navigate to login)
        }
    }
}
