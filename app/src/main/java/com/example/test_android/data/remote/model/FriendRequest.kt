package com.example.test_android.data.remote.model

import com.google.gson.annotations.SerializedName

data class FriendResquestResponse (
   val message: String,
   val status: Int
)

data class FriendRequestDto (
   val id: Int,
   @SerializedName("from_user") val fromUser: User,
   val timestamp: String
)