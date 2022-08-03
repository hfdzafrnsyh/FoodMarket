package com.example.foodmarket.model.response.transaction


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        @Expose
        @SerializedName("address")
        val address: String?=null,
        @Expose
        @SerializedName("city")
        val city: String?=null,
        @Expose
        @SerializedName("created_at")
        val createdAt: String?=null,
        @Expose
        @SerializedName("current_team_id")
        val currentTeamId: Int?=null,
        @Expose
        @SerializedName("email")
        val email: String?=null,
        @Expose
        @SerializedName("email_verified_at")
        val emailVerifiedAt: String?=null,
        @Expose
        @SerializedName("id")
        val id: Int?=null,
        @Expose
        @SerializedName("name")
        val name: String?=null,
        @Expose
        @SerializedName("number_home")
        val numberHome: String?=null,
        @Expose
        @SerializedName("number_phone")
        val numberPhone: String?=null,
        @Expose
        @SerializedName("profile_photo_path")
        val profilePhotoPath: String?=null,
        @Expose
        @SerializedName("profile_photo_url")
        val profilePhotoUrl: String?=null,
        @Expose
        @SerializedName("role")
        val role: String?=null,
        @Expose
        @SerializedName("two_factor_confirmed_at")
        val twoFactorConfirmedAt: String?=null,
        @Expose
        @SerializedName("updated_at")
        val updatedAt: String?=null
) :Parcelable