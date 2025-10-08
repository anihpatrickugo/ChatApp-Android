package com.example.test_android.data.remote.model

import com.example.test_android.ui.viewmodel.ChatMessage
import com.google.gson.annotations.SerializedName
import java.util.UUID



data class PrivateChatRoomDto(
    val id: Int,
    val participants: List<User>,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("last_message") val lastMessage: ChatMessage,
)
