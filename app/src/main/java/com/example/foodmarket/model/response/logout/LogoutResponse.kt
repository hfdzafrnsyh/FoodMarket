package com.example.foodmarket.model.response.logout


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LogoutResponse(
        @Expose
        @SerializedName("message")
        val message: String
)