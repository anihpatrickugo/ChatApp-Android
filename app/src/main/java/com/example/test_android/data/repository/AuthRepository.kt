package com.example.test_android.data.repository

import android.content.Context
import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.data.remote.model.LoginRequest
import com.example.test_android.data.remote.model.SignUpRequest

object AuthRepository {
    suspend fun login(username: String, password: String) =
        ApiClient.authService.login(LoginRequest(username, password))

    suspend fun signUp(
        username: String,
        email: String,
        password1: String,
        password2: String,
        firstName: String,
        lastName: String,
    ) =

        ApiClient.authService.signUp(SignUpRequest(
            username,
            email,
            password1,
            password2,
            firstName,
            lastName
        ))

    suspend fun getUserInfo() = ApiClient.authService.getUserInfo()

}


