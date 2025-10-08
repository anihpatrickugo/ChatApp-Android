package com.example.test_android.data.remote.api
import com.example.test_android.data.remote.model.LoginRequest
import com.example.test_android.data.remote.model.LoginResponse
import com.example.test_android.data.remote.model.RefreshResponse
import com.example.test_android.data.remote.model.SignUpRequest
import com.example.test_android.data.remote.model.SignUpResponse
import com.example.test_android.data.remote.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PATCH
import retrofit2.http.Part


interface AuthService {
    @POST("auth/token/")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/token/refresh/")
    suspend fun refreshAccessToken(@Body body: Map<String, String>): RefreshResponse

    @POST("auth/registration/")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @GET("auth/user/profile/")
    suspend fun getUserInfo(): Response<User>

    @Multipart
    @PATCH("auth/user/profile/")
    suspend fun editUserInfo(
        @Part("username") username: RequestBody?,
        @Part("email") email: RequestBody?,
        @Part("first_name") firstName: RequestBody?,
        @Part("last_name") lastName: RequestBody?,
        @Part photo: MultipartBody.Part?
    ): Response<User>

}
