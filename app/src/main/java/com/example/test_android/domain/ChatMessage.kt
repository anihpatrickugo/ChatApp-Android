package com.example.test_android.domain


data class ChatMessage(
    val id: Int,
    val text: String,
    val timestamp: Long,
    val sender: Sender
)

enum class Sender {
    ME, OTHER
}