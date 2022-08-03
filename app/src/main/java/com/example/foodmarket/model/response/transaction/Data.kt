package com.example.foodmarket.model.response.transaction


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
        @Expose
        @SerializedName("created_at")
        val createdAt: String,
        @Expose
        @SerializedName("food")
        val food: Food,
        @Expose
        @SerializedName("foodId")
        val foodId: Int,
        @Expose
        @SerializedName("id")
        val id: Int,
        @Expose
        @SerializedName("payment_url")
        val paymentUrl: String,
        @Expose
        @SerializedName("quantity")
        val quantity: Int,
        @Expose
        @SerializedName("status")
        val status: String,
        @Expose
        @SerializedName("total")
        val total: Int,
        @Expose
        @SerializedName("updated_at")
        val updatedAt: String,
        @Expose
        @SerializedName("user")
        val user: User,
        @Expose
        @SerializedName("userId")
        val userId: Int
):Parcelable