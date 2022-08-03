package com.example.foodmarket.ui.auth.signup

import android.net.Uri
import android.view.View
import com.example.foodmarket.model.request.RegisterRequest
import com.example.foodmarket.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class SignUpPresenter(private val view: SignUpContract.View) : SignUpContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun submitRegister(registerRequest: RegisterRequest,  viewParams : View) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.register(
                registerRequest.name.toString(),
                registerRequest.email.toString(),
                registerRequest.password.toString(),
                registerRequest.confirmed_password.toString(),
                registerRequest.address.toString(),
                registerRequest.number_phone.toString(),
                registerRequest.number_home.toString(),
                registerRequest.city.toString(),
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.dismissLoading()
                            if(it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onRegisterSuccess(it1,viewParams) }
                            }else{
                                it.meta?.message?.let { it1 -> view.onRegisterFailed(it1) }
                            }
                        },
                        {
                            view.dismissLoading()
                            view.onRegisterFailed(it?.message.toString())
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