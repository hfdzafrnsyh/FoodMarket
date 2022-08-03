package com.example.foodmarket.ui.ui.order

import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.response.transaction.TransactionResponse

interface OrderContract {

    interface View:BaseView{
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message : String)
    }

    interface Presenter : OrderContract , BasePresenter{
        fun getTransaction()
    }

}