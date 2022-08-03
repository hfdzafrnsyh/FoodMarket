package com.example.foodmarket.ui.auth.sign

import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.response.login.LoginResponse

interface SignContract {

    interface View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message:String)
    }

    interface Presenter:SignContract, BasePresenter {
        fun submitLogin(email : String , password : String)
    }

}