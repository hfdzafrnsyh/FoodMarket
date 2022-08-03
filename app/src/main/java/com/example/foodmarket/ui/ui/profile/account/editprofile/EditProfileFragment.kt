package com.example.foodmarket.ui.ui.profile.account.editprofile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.model.request.UserRequest
import com.example.foodmarket.model.response.login.User
import com.example.foodmarket.model.response.updateprofile.UpdateProfileResponse
import com.example.foodmarket.ui.MainActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_edit_profile.*



class EditProfileFragment : Fragment(),UpdateProfileContract.View {


    private lateinit var userReqeust : UserRequest
    private var progressBar : Dialog?=null
    private lateinit var presenter : UpdateProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = UpdateProfilePresenter(this)

        initViewLoading()
        initViewUpdateProfile()
    }




    private fun initViewUpdateProfile(){
        val gson = Gson()
        val user = gson.fromJson(FoodMarket.getApp().getUser(), User::class.java)

        edtName.setText("${user.name}")
        edtPhone.setText("${user.number_phone}")
        edtNoHome.setText("${user.number_home}")
        edtCity.setText("${user.city}")
        edtAddress.setText("${user.address}")


        btnUpdateProfile.setOnClickListener {

            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            val noHome = edtNoHome.text.toString()
            val city = edtCity.text.toString()
            val address = edtAddress.text.toString()

            if(name.isNullOrEmpty()){
                edtName.error ="Type your name"
                edtName.requestFocus()
            }else if(phone.isNullOrEmpty()){
                edtPhone.error="Type your phone"
                edtPhone.requestFocus()
            }else if(noHome.isNullOrEmpty()){
                edtNoHome.error="Type your no home"
                edtNoHome.requestFocus()
            }else if(city.isNullOrEmpty()){
                edtCity.error="Type your city"
                edtCity.requestFocus()
            }else if(address.isNullOrEmpty()){
                edtAddress.error="Type your address"
                edtAddress.requestFocus()
            }else {

                userReqeust = UserRequest(
                        name,
                        phone,
                        noHome,
                        city,
                        address)

                presenter.submitUpdateProfile(userReqeust)

            }


        }


    }


    override fun onUpdateProfileSuccess(updateProfileResponse: UpdateProfileResponse) {


        val gson = Gson()
        val updateUsers = gson.toJson(updateProfileResponse.user)
        FoodMarket.getApp().setUser(updateUsers)

        val intent = Intent(activity,MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onUpdateProfileFailed(message: String) {
        Toast.makeText(activity,"Failed Update Profile" , Toast.LENGTH_SHORT).show()
    }

    fun initViewLoading(){
        progressBar = Dialog(requireContext())
        val progressBarLayout = layoutInflater.inflate(R.layout.dialog_progress_bar , null)

        progressBar?.let {
            it.setContentView(progressBarLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

    }


    override fun showLoading() {
        progressBar?.show()
    }

    override fun dismissLoading() {
        progressBar?.dismiss()
    }


}