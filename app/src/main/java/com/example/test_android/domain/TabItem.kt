package com.example.test_android.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabItem(val title: String) {

    // without icon
    object Chats : TabItem("Chats")
    object Groups : TabItem("Groups")
    object Friends : TabItem("Friends")
}

val tabItems = listOf(
    TabItem.Chats,
    TabItem.Groups,
    TabItem.Friends
)