package com.example.foodmarket.ui.ui.detail.payment

import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.request.CheckoutRequest
import com.example.foodmarket.model.response.checkout.CheckoutResponse

interface PaymentContract {

    interface View : BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse , view : android.view.View)
        fun onCheckoutFailed(message : String)
    }

    interface Presenter : PaymentContract , BasePresenter {
        fun getCheckout( checkoutRequest: CheckoutRequest,view : android.view.View)
    }
}