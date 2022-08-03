package com.example.foodmarket.ui.ui.profile.account.editprofile

import com.example.foodmarket.model.request.UserRequest
import com.example.foodmarket.network.HttpClient
import com.example.foodmarket.ui.auth.sign.SignContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class UpdateProfilePresenter(private val view: UpdateProfileContract.View) : UpdateProfileContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitUpdateProfile(userRequest: UserRequest) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.updateProfile(
                userRequest.name.toString(),
                userRequest.number_phone.toString(),
                userRequest.number_home.toString(),
                userRequest.city.toString(),
                userRequest.address.toString()
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.dismissLoading()
                            if(it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onUpdateProfileSuccess(it1) }
                            }else{
                                it.meta?.message?.let { it1 -> view.onUpdateProfileFailed(it1) }
                            }
                        },
                        {
                            view.dismissLoading()
                            view.onUpdateProfileFailed(it?.message.toString())
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
