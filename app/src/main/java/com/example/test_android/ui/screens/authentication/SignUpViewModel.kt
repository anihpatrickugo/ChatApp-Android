package com.example.test_android.ui.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    data class Success(val key: String) : SignUpState()
    data class Error(val message: String) : SignUpState()
}




class SignUpViewModel(
) : ViewModel() {

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun signUp(
        username: String,
        email: String,
        password1: String,
        password2: String,
        firstName: String,
        lastName: String,
    ) {
        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            try {
                val response = AuthRepository.signUp(username, email, password1, password2, firstName, lastName)

                _signUpState.value = SignUpState.Success(response.key)
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(e.message ?: "Signup failed")
            }
        }
    }
}
