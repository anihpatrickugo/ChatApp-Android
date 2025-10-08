package com.example.test_android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.data.remote.websocket.ChatWebSocketClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),  // temporary unique id
    val roomId: Int,
    val user: String,
    val message: String,
    val timestamp: String,
    val pending: Boolean = false                    // NEW: track if it's pending confirmation
)

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private var webSocketClient: ChatWebSocketClient? = null


    // 🔹 Fetch previous messages from REST
    fun loadHistory(roomId: Int) {
        viewModelScope.launch {
            try {
                val history = ApiClient.chatApi.getRoomMessages(roomId).map {
                    ChatMessage(
                        id = it.id.toString(),
                        roomId = it.room,
                        user = it.sender,
                        message = it.message,
                        timestamp = it.timestamp,
                        pending = false
                    )
                }
                // merge REST history + existing WS updates
                _messages.value = history
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /** Connects to WebSocket using the latest token */
    fun connect() {
        viewModelScope.launch {
            val client = ApiClient.getWebSocketClient()
            if (client != null) {
                webSocketClient = client
                client.onMessageReceived = { json ->
                    val msg = parseMessage(json)
                    if (msg != null) {
                        // Replace pending version if exists
                        val updatedList = _messages.value.map { existing ->
                            if (existing.message == msg.message &&
                                existing.roomId == msg.roomId &&
                                existing.pending
                            ) {
                                msg
                            } else existing
                        }

                        // If not found → just add
                        val newList =
                            if (updatedList == _messages.value) _messages.value + msg
                            else updatedList

                        _messages.value = newList
                    }
                }
                client.connect()
            }
        }
    }

    /** Send message with optimistic UI update */
    fun sendMessage(roomId: Int, message: String, username: String) {
        val pendingMsg = ChatMessage(
            roomId = roomId,
            user = username,
            message = message,
            timestamp = System.currentTimeMillis().toString(),
            pending = true
        )

        // Send to server
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
                timestamp = obj.getString("timestamp"),
                pending = false // server messages are confirmed
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
