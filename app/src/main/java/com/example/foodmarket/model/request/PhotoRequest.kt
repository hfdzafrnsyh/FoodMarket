package com.example.foodmarket.model.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Field

data class PhotoRequest (
        @Expose
        @SerializedName("file")
        var file : Any? = null
)
