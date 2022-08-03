package com.example.foodmarket.ui.ui.home

import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.response.home.HomeResponse

interface HomeContract {

    interface View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message : String)
    }

    interface Presenter: HomeContract , BasePresenter{
        fun getHome()
    }

}