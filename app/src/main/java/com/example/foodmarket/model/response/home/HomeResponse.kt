package com.example.foodmarket.model.response.home


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class HomeResponse(

        @Expose
        @SerializedName("current_page")
        val currentPage: Int,
        @Expose
        @SerializedName("data")
        val data : List<Data>,
        @Expose
        @SerializedName("first_page_url")
        val firstPageUrl: String,
        @Expose
        @SerializedName("from")
        val from: Int,
        @Expose
        @SerializedName("last_page")
        val lastPage: Int,
        @Expose
        @SerializedName("last_page_url")
        val lastPageUrl: String,
        @Expose
        @SerializedName("links")
        val links: List<Link>,
        @Expose
        @SerializedName("next_page_url")
        val nextPageUrl: String,
        @Expose
        @SerializedName("path")
        val path: String,
        @Expose
        @SerializedName("per_page")
        val perPage: Int,
        @Expose
        @SerializedName("prev_page_url")
        val prevPageUrl: Any,
        @Expose
        @SerializedName("to")
        val to: Int,
        @Expose
        @SerializedName("total")
        val total: Int
)