package com.example.foodmarket.ui.ui.home

import com.example.foodmarket.network.HttpClient
import com.example.foodmarket.ui.auth.sign.SignContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getHome() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.food()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.dismissLoading()
                            if(it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onHomeSuccess(it1) }
                            }else{
                                it.meta?.message?.let { it1 -> view.onHomeFailed(it1) }
                            }
                        },
                        {
                            view.dismissLoading()
                            view.onHomeFailed(it?.message.toString())
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
