package com.example.foodmarket.ui.ui.profile

import android.net.Uri
import android.view.View
import com.example.foodmarket.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProfilePresenter(private var view : ProfileContract.View) : ProfileContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitLogout(viewParams : View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.dismissLoading()
                            if(it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onLogoutSuccess(it1,viewParams) }
                            }else{
                                it.meta?.message?.let { it1 -> view.onLogoutFailed(it1) }
                            }
                        },
                        {
                            view.dismissLoading()
                            view.onLogoutFailed(it?.message.toString())
                        }
                )

        mCompositeDisposable!!.add(disposable)
    }

    override fun submitPhoto(profilePhotoPath : Uri) {
        view.showLoading()

        val photoProfile = File(profilePhotoPath.path)
        val profilePhotoReqBody = RequestBody.create(MediaType.parse("multipart/form-data" ), photoProfile)
        val profilePhotoParms = MultipartBody.Part.createFormData("file" , photoProfile.name , profilePhotoReqBody)


        val disposable = HttpClient.getInstance().getApi()!!.updatePhoto(
                profilePhotoParms
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.dismissLoading()
                            if(it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onUpdatePhotoSuccess(it1) }
                            }else{
                                it.meta?.message?.let { it1 -> view.onUpdatePhotoFailed(it1) }
                            }
                        },
                        {
                            view.dismissLoading()
                            view.onUpdatePhotoFailed(it?.message.toString())
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