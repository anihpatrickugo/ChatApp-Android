package com.example.test_android.data.remote.api
import com.example.test_android.data.remote.model.LoginRequest
import com.example.test_android.data.remote.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST



interface AuthService {
    @POST("auth/token/")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
