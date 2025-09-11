package com.example.test_android

import android.app.Application
import com.example.test_android.data.local.TokenManager
import com.example.test_android.data.remote.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MyApp : Application() {
    companion object {
        var startDestination: String = "onboarding"  // default
    }

    override fun onCreate() {
        super.onCreate()
        ApiClient.init(this)

        // ✅ Check if token exists
        val tokenManager = TokenManager(this)

        // We need to block here once since Application.onCreate is not suspend
        val accessToken = runBlocking {
            tokenManager.getAccessToken()
        }

        startDestination = if (!accessToken.isNullOrEmpty()) {
            "home"  // go directly to dashboard
        } else {
            "onboarding"
        }
    }
}
