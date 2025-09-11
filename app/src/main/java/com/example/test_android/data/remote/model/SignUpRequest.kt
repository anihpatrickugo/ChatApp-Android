package com.example.test_android.data.remote.model

import com.google.gson.annotations.SerializedName

data class SignUpRequest (
   val username: String,
   val email: String,
   val password1: String,
   val password2: String,
   @SerializedName("first_name") val firstName: String,
   @SerializedName("last_name") val lastName: String
)