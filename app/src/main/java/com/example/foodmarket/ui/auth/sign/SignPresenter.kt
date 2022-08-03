package com.example.foodmarket.ui.auth.sign

import com.example.foodmarket.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignPresenter(private val view:SignContract.View) : SignContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitLogin(email: String, password: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.login(email,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                view.dismissLoading()
                if(it.meta?.status.equals("success",true)){
                 it.data?.let { it1 -> view.onLoginSuccess(it1) }
                }else{
                    it.meta?.message?.let { it1 -> view.onLoginFailed(it1) }
                }
            },
                {
                    view.dismissLoading()
                    view.onLoginFailed(it?.message.toString())
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

