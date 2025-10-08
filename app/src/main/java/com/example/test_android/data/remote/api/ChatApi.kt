package com.example.test_android.data.remote.api

import com.example.test_android.data.remote.model.ChatMessageDto
import com.example.test_android.data.remote.model.PrivateChatRoomDto
import com.example.test_android.data.remote.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatApi {
    @GET("chat-list/")
    suspend fun getPrivateRooms(): List<PrivateChatRoomDto>

    @GET("chatroom/{roomId}/friend/")
    suspend fun getFriendInRoom(
        @Path("roomId") roomId: Int
    ): User

    @GET("chatroom/{roomId}/messages/")
    suspend fun getRoomMessages(
        @Path("roomId") roomId: Int
    ): List<ChatMessageDto>
}

