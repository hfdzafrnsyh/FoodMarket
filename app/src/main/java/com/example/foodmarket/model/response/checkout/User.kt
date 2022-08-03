package com.example.foodmarket.model.response.checkout


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

        @Expose
        @SerializedName("address")
        val address: String,
        @Expose
        @SerializedName("city")
        val city: String,
        @Expose
        @SerializedName("created_at")
        val createdAt: String,
        @Expose
        @SerializedName("current_team_id")
        val currentTeamId: Int,
        @Expose
        @SerializedName("email")
        val email: String,
        @Expose
        @SerializedName("email_verified_at")
        val emailVerifiedAt: Any,
        @Expose
        @SerializedName("id")
        val id: Int,
        @Expose
        @SerializedName("name")
        val name: String,
        @Expose
        @SerializedName("number_home")
        val numberHome: String,
        @Expose
        @SerializedName("number_phone")
        val numberPhone: String,
        @Expose
        @SerializedName("profile_photo_path")
        val profilePhotoPath: String,
        @Expose
        @SerializedName("profile_photo_url")
        val profilePhotoUrl: String,
        @Expose
        @SerializedName("role")
        val role: String,
        @Expose
        @SerializedName("two_factor_confirmed_at")
        val twoFactorConfirmedAt: Any,
        @Expose
        @SerializedName("updated_at")
        val updatedAt: String
)