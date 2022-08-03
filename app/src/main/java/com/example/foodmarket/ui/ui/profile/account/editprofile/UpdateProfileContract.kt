package com.example.foodmarket.ui.ui.profile.account.editprofile

import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.request.UserRequest
import com.example.foodmarket.model.response.updateprofile.UpdateProfileResponse

interface UpdateProfileContract {

    interface View: BaseView {
        fun onUpdateProfileSuccess(updateProfileResponse: UpdateProfileResponse)
        fun onUpdateProfileFailed(message:String)
    }

    interface Presenter: UpdateProfileContract, BasePresenter {
        fun submitUpdateProfile(userRequest: UserRequest)
    }
}