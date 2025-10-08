package com.example.test_android.data.remote.api
import android.content.Context
import android.util.Log
import com.example.test_android.data.local.TokenManager
import com.example.test_android.data.remote.websocket.ChatWebSocketClient
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val ENDPOINT = "chatapp-p6tu.onrender.com"
private const val BASE_URL = "https://${ENDPOINT}/"
private const val WS_URL = "wss://${ENDPOINT}/ws/chat/"


class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // Fetch access token
        val accessToken = runBlocking { tokenManager.getAccessToken() }
        if (!accessToken.isNullOrEmpty()) {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        }

        var response = chain.proceed(request)

        // If unauthorized, try refreshing the token
        if (response.code == 401) {
            response.close() // Close the old response

            val newAccessToken = refreshToken()

            if (!newAccessToken.isNullOrEmpty()) {
                // Retry original request with new access token
                val newRequest = request.newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", "Bearer $newAccessToken")
                    .build()

                response = chain.proceed(newRequest)
            }
        }

        return response
    }

    private fun refreshToken(): String? {
        val refreshToken = runBlocking { tokenManager.getRefreshToken() }
        if (refreshToken.isNullOrEmpty()) return null

        return try {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) // same base url
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(AuthService::class.java)

            val refreshResponse = runBlocking {
                service.refreshAccessToken(mapOf("refresh" to refreshToken))
            }

            // Save the new access token
            runBlocking { tokenManager.saveTokens(refreshResponse.access, refreshResponse.refresh) }

            refreshResponse.access
        } catch (e: Exception) {
            Log.e("AuthInterceptor", "Refresh token failed: ${e.message}")
            null
        }
    }
}


object ApiClient {
    private lateinit var tokenManager: TokenManager

    fun init(context: Context) {
        tokenManager = TokenManager(context)
    }

    private val client by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(tokenManager))
            .addInterceptor(logging) // log requests and responses
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val chatApi: ChatApi by lazy {
        retrofit.create(ChatApi::class.java)
    }

    val friendRequestService: FriendRequestService by lazy {
        retrofit.create(FriendRequestService::class.java)
    }

    suspend fun getWebSocketClient(): ChatWebSocketClient? {
        val token = tokenManager.getAccessToken()

        return if (!token.isNullOrEmpty()) {
            ChatWebSocketClient(token, WS_URL)
        } else null
    }
}
