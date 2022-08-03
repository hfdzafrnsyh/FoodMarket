package com.example.foodmarket.model.request

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRequest(

        @Expose
        @SerializedName("name")
        var name : String? = null,
        @Expose
        @SerializedName("number_phone")
        var number_phone : String? = null,
        @Expose
        @SerializedName("number_home")
        var number_home : String? = null,
        @Expose
        @SerializedName("city")
        var city : String? = null,
        @Expose
        @SerializedName("address")
        var address : String? = null,

): Parcelable
