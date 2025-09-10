package com.example.test_android.data.repository

import android.content.Context
import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.data.remote.model.LoginRequest

object AuthRepository {
    suspend fun login(username: String, password: String) =
        ApiClient.authService.login(LoginRequest(username, password))
}


