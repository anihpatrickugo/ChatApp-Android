package com.example.test_android.data.remote.api

import com.example.test_android.data.remote.model.*
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface FriendRequestService {

    @GET("list-request/")
    suspend fun ListFriendRequest(): List<FriendRequestDto>
    
    @POST("send-request/{userId}/")
    suspend fun sendFriendRequest(
        @Path("userId") userId: Int
    ): FriendResquestResponse


    @POST("decline-request/{requestId}/")
    suspend fun declineFriendRequest(
        @Path("requestId") requestId: Int
    ): FriendResquestResponse

    @POST("accept-request/{requestId}/")
    suspend fun acceptFriendRequest(
        @Path("requestId") requestId: Int
    ): FriendResquestResponse
}

