package com.example.foodmarket.ui.ui.detail.payment

import android.view.View
import com.example.foodmarket.model.request.CheckoutRequest
import com.example.foodmarket.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PaymentPresenter(private val view: PaymentContract.View) : PaymentContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getCheckout(checkoutRequest: CheckoutRequest, viewParams : View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.checkout(
                checkoutRequest.userId.toString(),
                checkoutRequest.foodId.toString(),
                checkoutRequest.quantity.toString(),
                checkoutRequest.total.toString(),
                checkoutRequest.status.toString()
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.dismissLoading()
                            if(it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onCheckoutSuccess(it1, viewParams) }
                            }else{
                                it.meta?.message?.let { it1 -> view.onCheckoutFailed(it1) }
                            }
                        },
                        {
                            view.dismissLoading()
                            view.onCheckoutFailed(it?.message.toString())
                        }
                )

        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }


}
