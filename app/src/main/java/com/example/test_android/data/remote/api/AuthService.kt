package com.example.test_android.data.remote.api
import com.example.test_android.data.remote.model.LoginRequest
import com.example.test_android.data.remote.model.LoginResponse
import com.example.test_android.data.remote.model.RefreshResponse
import com.example.test_android.data.remote.model.SignUpRequest
import com.example.test_android.data.remote.model.SignUpResponse
import com.example.test_android.data.remote.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



interface AuthService {
    @POST("auth/token/")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/registration/")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @GET("auth/user/")
    suspend fun getUserInfo(): Response<User>

    @POST("auth/token/refresh/")
    suspend fun refreshAccessToken(@Body body: Map<String, String>): RefreshResponse
}
