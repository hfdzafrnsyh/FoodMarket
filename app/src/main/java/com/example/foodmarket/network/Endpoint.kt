package com.example.foodmarket.network

import com.example.foodmarket.model.response.Wrapper
import com.example.foodmarket.model.response.checkout.CheckoutResponse
import com.example.foodmarket.model.response.home.HomeResponse
import com.example.foodmarket.model.response.login.LoginResponse
import com.example.foodmarket.model.response.logout.LogoutResponse
import com.example.foodmarket.model.response.transaction.TransactionResponse
import com.example.foodmarket.model.response.updateprofile.UpdateProfileResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
        ): Observable<Wrapper<LoginResponse>>


    @FormUrlEncoded
    @POST("register")
    fun register(
            @Field("name") name: String,
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("confirmed_password") confirmed_password: String,
            @Field("address") address: String,
            @Field("number_phone") number_phone: String,
            @Field("number_home") number_home: String,
            @Field("city") city: String,
    ) : Observable<Wrapper<LoginResponse>>



    @GET("food")
    @Headers("Accept: application/json")
    fun food() : Observable<Wrapper<HomeResponse>>



    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("checkout")
    fun checkout(
            @Field("userId") userId: String,
            @Field("foodId") foodId: String,
            @Field("quantity") quantity: String,
            @Field("total") total: String,
            @Field("status") status: String,
    ) : Observable<Wrapper<CheckoutResponse>>


    @GET("transaction")
    @Headers("Accept: application/json")
    fun transaction() : Observable<Wrapper<TransactionResponse>>


    @FormUrlEncoded
    @Headers("Accept: application/json")
    @PUT("users/update")
    fun updateProfile(
            @Field("name") name:String,
            @Field("number_phone") number_phone : String,
            @Field("number_home") number_home:String,
            @Field("city") city : String,
            @Field("address") address:String
    ) : Observable<Wrapper<UpdateProfileResponse>>

    @Multipart
    @Headers("Accept: application/json")
    @POST("users/update/photo")
    fun updatePhoto(
            @Part profilePhotoPath : MultipartBody.Part
    ) : Observable<Wrapper<UpdateProfileResponse>>


    @Headers("Accept: application/json")
    @POST("logout")
    fun logout() : Observable<Wrapper<LogoutResponse>>

}