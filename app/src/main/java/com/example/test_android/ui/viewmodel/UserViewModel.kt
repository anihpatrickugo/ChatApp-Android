package com.example.test_android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.local.TokenManager
import com.example.test_android.data.remote.model.User
import com.example.test_android.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.io.File

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val user: User) : UserState()
    data class Error(val message: String) : UserState()
}


sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    object NavigateBack : UiEvent()
}

class UserViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    // 👇 One-time UI events
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()



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

    fun updateProfile(
        username: String? = null,
        email: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        photo: File? = null
    ) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val response = AuthRepository.editUserInfo(
                        username = username,
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        photo = photo
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        _userState.value = UserState.Success(it)

                        // 👇 send success events
                        _uiEvent.send(UiEvent.ShowToast("Profile updated successfully!"))
                        _uiEvent.send(UiEvent.NavigateBack)

                    } ?: run {
                        _userState.value = UserState.Error("Empty response")
                        _uiEvent.send(UiEvent.ShowToast("Empty response"))
                    }
                } else {
                    _userState.value = UserState.Error("Error: ${response.code()}")
                    _uiEvent.send(UiEvent.ShowToast("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error("Exception: ${e.localizedMessage}")
                _uiEvent.send(UiEvent.ShowToast("Error: ${e.localizedMessage}"))
            }
        }
    }

}
