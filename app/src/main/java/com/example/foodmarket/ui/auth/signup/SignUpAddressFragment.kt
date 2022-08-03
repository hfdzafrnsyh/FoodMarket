package com.example.foodmarket.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.model.request.RegisterRequest
import com.example.foodmarket.model.response.login.LoginResponse
import com.example.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_up_address.*


class SignUpAddressFragment : Fragment(), SignUpContract.View,View.OnClickListener {

    companion object{
        val DATA_USER = "DATA_USER"
    }

    private lateinit var user : RegisterRequest
      var progressBar : Dialog? = null
    private lateinit var presenter : SignUpPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignUpPresenter(this)
        btnSignUpNow.setOnClickListener(this)

        initViewLoading()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnSignUpNow -> {
                actionRegisterAddress(view)
            }
        }
    }

    private fun actionRegisterAddress(view : View?){

        view?.let{

            val numberPhone = edtNumberPhone.text.toString()
            val numberHome = edtNumberHome.text.toString()
            val address = edtAddress.text.toString()
            val city = edtCity.text.toString()

            if(numberPhone.isNullOrEmpty()){
                edtNumberPhone.error = "Type Your Phone Number"
                edtNumberPhone.requestFocus()
            }else if(numberHome.isNullOrEmpty()){
                edtNumberHome.error = "Type Your Home Number"
                edtNumberHome.requestFocus()
            }else if(address.isNullOrEmpty()){
                edtAddress.error = "Type Your Address"
                edtAddress.requestFocus()
            }else if(city.isNullOrEmpty()){
                edtCity.error = "Type Your City"
                edtCity.requestFocus()
            }else{
                user = arguments?.getParcelable<RegisterRequest>(DATA_USER)!!

                user?.let {
                    it.number_phone = numberPhone
                    it.number_home = numberHome
                    it.address = address
                    it.city = city
                }

                Log.d("DATA_USER" , user.toString())
                presenter.submitRegister(user,it)

            }

        }

    }

    override fun onRegisterSuccess(loginRespone: LoginResponse, view: View) {

        FoodMarket.getApp().setToken(loginRespone.access_token)

        val gson = Gson()
        val users = gson.toJson(loginRespone.user)
        FoodMarket.getApp().setUser(users)

                    Navigation.findNavController(view)
                    .navigate(R.id.fragmentSignUpSuccess , null)

            (activity as AuthActivity).toolbarSignUpSuccess();

    }


    override fun onRegisterFailed(message: String) {
        Toast.makeText(context , "${message.toString()}" , Toast.LENGTH_SHORT).show()
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