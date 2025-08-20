package com.example.test_android.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabItem(val title: String) {

    // without icon
    object Home : TabItem("Home")
    object Profile : TabItem("Profile")
    object Settings : TabItem("Settings")
}

val tabItems = listOf(
    TabItem.Home,
    TabItem.Profile,
    TabItem.Settings
)