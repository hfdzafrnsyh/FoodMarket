package com.example.foodmarket

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.example.foodmarket.network.HttpClient

class FoodMarket : MultiDexApplication() {

    companion object{
        lateinit var instance : FoodMarket

        fun getApp() : FoodMarket{
            return instance
        }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPrefences() : SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(this)
    }


    fun setUser(user : String){
        getPrefences().edit().putString("PREFERENCES_USER" , user).apply()
    }

    fun getUser() : String? {
        return getPrefences().getString("PREFERENCES_USER",null)
    }

    fun setToken(token : String){
        getPrefences().edit().putString("PREFERENCES_TOKEN" , token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken() : String? {
        return getPrefences().getString("PREFERENCES_TOKEN",null)
    }

}