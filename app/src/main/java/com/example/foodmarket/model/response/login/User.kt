package com.example.foodmarket.model.response.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("address")
    val address: String?=null,
    @Expose
    @SerializedName("city")
    val city: String,
    @Expose
    @SerializedName("created_at")
    val created_at: String,
    @Expose
    @SerializedName("current_team_id")
    val current_team_id: Int,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("email_verified_at")
    val email_verified_at: Any,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("number_home")
    val number_home: Any,
    @Expose
    @SerializedName("number_phone")
    val number_phone: Any,
    @Expose
    @SerializedName("profile_photo_path")
    val profile_photo_path: Any,
    @Expose
    @SerializedName("profile_photo_url")
    val profile_photo_url: String,
    @Expose
    @SerializedName("role")
    val role: String,
    @Expose
    @SerializedName("two_factor_confirmed_at")
    val two_factor_confirmed_at: Any,
    @Expose
    @SerializedName("update_at")
    val updated_at: String
)