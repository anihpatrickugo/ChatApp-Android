package com.example.test_android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.local.TokenManager
import com.example.test_android.data.remote.model.User
import com.example.test_android.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val user: User) : UserState()
    data class Error(val message: String) : UserState()
}

class UserViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            tokenManager.clearTokens()
            onLoggedOut() // notify UI (e.g., navigate to login)
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val response = AuthRepository.getUserInfo() // <-- create this in ApiService
                if (response.isSuccessful) {
                    val user = response.body()!!
                    _userState.value = UserState.Success(user)
                } else {
                    _userState.value = UserState.Error("Failed: ${response.code()}")
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }
}
