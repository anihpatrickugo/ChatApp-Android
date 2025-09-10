package com.example.test_android.ui.screens.authentication
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.local.TokenManager
import com.example.test_android.data.remote.model.User
import com.example.test_android.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val refresh: String, val access: String) : LoginState()
    data class Error(val message: String) : LoginState()
}




class LoginViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = AuthRepository.login(username, password)

                // 👇 Save tokens securely in DataStore
                tokenManager.saveTokens(response.access, response.refresh)

                _loginState.value = LoginState.Success(response.refresh, response.access)
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Login failed")
            }
        }
    }
}
