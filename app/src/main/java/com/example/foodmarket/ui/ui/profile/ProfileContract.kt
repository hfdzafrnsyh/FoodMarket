package com.example.foodmarket.ui.ui.profile

import android.net.Uri
import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.response.logout.LogoutResponse
import com.example.foodmarket.model.response.updateprofile.UpdateProfileResponse

interface ProfileContract {

    interface View : BaseView{
        fun onUpdatePhotoSuccess(updateProfileResponse: UpdateProfileResponse)
        fun onUpdatePhotoFailed(message : String)
        fun onLogoutSuccess(logoutResponse: LogoutResponse, view : android.view.View)
        fun onLogoutFailed(mesage : String)
    }

    interface Presenter:ProfileContract,BasePresenter{
        fun submitLogout(view : android.view.View)
        fun submitPhoto(profilePhotoPath:Uri)
    }

}