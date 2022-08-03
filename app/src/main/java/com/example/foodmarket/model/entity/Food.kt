package com.example.foodmarket.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    var title : String? = null,
    var poster : String? = null,
    var price : String? = null,
    var rating : Float = 0f
):Parcelable
