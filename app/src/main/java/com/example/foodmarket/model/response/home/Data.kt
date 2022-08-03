package com.example.foodmarket.model.response.home


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
        @Expose
        @SerializedName("created_at")
        val createdAt: String?,
        @Expose
        @SerializedName("description")
        val description: String?,
        @Expose
        @SerializedName("id")
        val id: Int?,
        @Expose
        @SerializedName("ingredients")
        val ingredients: String?,
        @Expose
        @SerializedName("name")
        val name: String?,
        @Expose
        @SerializedName("picture_path")
        val picturePath: String?,
        @Expose
        @SerializedName("price")
        val price: Int?,
        @Expose
        @SerializedName("rating")
        val rating: String?,
        @Expose
        @SerializedName("type")
        val type: String?,
        @Expose
        @SerializedName("updated_at")
        val updatedAt: String?
):Parcelable