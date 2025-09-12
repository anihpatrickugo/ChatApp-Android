package com.example.test_android.data.remote.websocket

import android.util.Log
import okhttp3.*
import okio.ByteString

class ChatWebSocketClient(
    private val token: String,
    private val baseUrl: String
) : WebSocketListener() {

    private var webSocket: WebSocket? = null
    var onMessageReceived: ((String) -> Unit)? = null

    fun connect() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(baseUrl)
            .addHeader("Authorization", token)
            .build()

        webSocket = client.newWebSocket(request, this)
        client.dispatcher.executorService.shutdown()
    }

    fun sendMessage(roomId: Int, message: String) {
        val jsonMessage = """
            {
                "room_id": $roomId,
                "message": "$message"
            }
        """.trimIndent()
        webSocket?.send(jsonMessage)
    }

    fun close() {
        webSocket?.close(1000, "Client closed connection")
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.d("WebSocket", "Connected to WebSocket")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Log.d("WebSocket", "Received: $text")
        onMessageReceived?.invoke(text)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.d("WebSocket", "Received bytes: ${bytes.hex()}")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d("WebSocket", "Closing: $code / $reason")
        webSocket.close(1000, null)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.e("WebSocket", "Error: ${t.message}")
    }
}
