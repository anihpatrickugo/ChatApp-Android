package com.example.test_android.data.repository

import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.data.remote.model.LoginRequest
import com.example.test_android.data.remote.model.SignUpRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


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

    suspend fun editUserInfo(
        username: String? = null,
        email: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        photo: File? = null
    ) = ApiClient.authService.editUserInfo(
        username = username?.toRequestBody("text/plain".toMediaTypeOrNull()),
        email = email?.toRequestBody("text/plain".toMediaTypeOrNull()),
        firstName = firstName?.toRequestBody("text/plain".toMediaTypeOrNull()),
        lastName = lastName?.toRequestBody("text/plain".toMediaTypeOrNull()),
        photo = photo?.let {
            val requestFile = it.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("photo", it.name, requestFile)
        }
    )



}


