package com.example.test_android.data.remote.model

data class ChatMessageDto (
    val id: Int,
    val room: Int,
    val sender: String,
    val message: String,
    val timestamp: String
)