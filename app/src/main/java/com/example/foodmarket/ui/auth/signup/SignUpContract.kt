package com.example.foodmarket.ui.auth.signup

import android.net.Uri
import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.request.RegisterRequest
import com.example.foodmarket.model.response.login.LoginResponse

interface SignUpContract {

    interface View : BaseView {
        fun onRegisterSuccess(loginRespone:LoginResponse, view : android.view.View)
        fun onRegisterFailed(message : String)
    }

    interface Presenter : SignUpContract , BasePresenter{
        fun submitRegister(registerRequest: RegisterRequest  ,view : android.view.View)
    }
}