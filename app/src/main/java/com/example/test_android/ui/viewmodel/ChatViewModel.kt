package com.example.test_android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.data.remote.websocket.ChatWebSocketClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

data class ChatMessage(
    val roomId: Int,
    val user: String,
    val message: String,
    val timestamp: String
)

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private var webSocketClient: ChatWebSocketClient? = null

    /** Connects to WebSocket using the latest token */
    fun connect() {
        viewModelScope.launch {
            val client = ApiClient.getWebSocketClient()  // fetches fresh token
            if (client != null) {
                webSocketClient = client
                client.onMessageReceived = { json ->
                    val msg = parseMessage(json)
                    if (msg != null) {
                        _messages.value = _messages.value + msg
                    }
                }
                client.connect()
            }
        }
    }

    fun sendMessage(roomId: Int, message: String) {
        webSocketClient?.sendMessage(roomId, message)
    }

    fun disconnect() {
        webSocketClient?.close()
    }

    private fun parseMessage(json: String): ChatMessage? {
        return try {
            val obj = JSONObject(json)
            ChatMessage(
                roomId = obj.getInt("room_id"),
                user = obj.getString("user"),
                message = obj.getString("message"),
                timestamp = obj.getString("timestamp")
            )
        } catch (e: Exception) {
            null
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}
