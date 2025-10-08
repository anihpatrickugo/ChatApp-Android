package com.example.test_android.data.remote.model
import com.google.gson.annotations.SerializedName

data class User(
    val pk: Int?,
    val id: Int?,
    val objectID: Int?,
    val username: String,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("get_photo") val getPhoto: String?,
    val photo: String,

)

data class SearchUser(
    val id: Int?,
    val objectID: Int,
    val username: String,
    val email: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("get_photo") val getPhoto: String?,
    )

