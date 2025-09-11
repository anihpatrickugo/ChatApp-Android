package com.example.test_android.ui.providers

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.test_android.ui.viewmodel.UserViewModel

// Define a global CompositionLocal for UserViewModel
val LocalUserViewModel = staticCompositionLocalOf<UserViewModel> {
    error("No UserViewModel provided")
}