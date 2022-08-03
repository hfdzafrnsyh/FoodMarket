package com.example.foodmarket.ui.auth.sign

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.ui.MainActivity
import com.example.foodmarket.ui.auth.AuthActivity
import com.example.foodmarket.model.response.login.LoginResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_sign_in.*


class SignInFragment : Fragment(),SignContract.View , View.OnClickListener  {

    lateinit var presenter : SignPresenter
    private var progressBar : Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignPresenter(this)

        if(!FoodMarket.getApp().getUser().isNullOrEmpty()){
            val intent = Intent(activity,MainActivity::class.java);
            startActivity(intent)
            activity?.finish()
        }

        btnSignUp.setOnClickListener(this)
        btnSignIn.setOnClickListener(this)

        initViewLoading()
    }

    override fun onClick(view: View?) {
            when(view?.id){
                R.id.btnSignUp -> {
                    val intent = Intent(activity,AuthActivity::class.java)
                    intent.putExtra("PAGE_REQUEST" , 2);
                    startActivity(intent)
                }
                R.id.btnSignIn -> {
                    loginAction()
                }
            }
    }


    private fun loginAction(){
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        if(email.isNullOrEmpty()){
            edtEmail.error = "Type your Email"
            edtEmail.requestFocus()
        }else if(password.isNullOrEmpty()){
            edtPassword.error = "Type your password"
            edtPassword.requestFocus()
        }else{
            presenter.submitLogin(email , password)
        }

    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {

        val gson = Gson()
        val users = gson.toJson(loginResponse.user)

        FoodMarket.getApp().setUser(users)
        FoodMarket.getApp().setToken(loginResponse.access_token)

        Log.d("auth token" , loginResponse.access_token)

        val intent = Intent(activity,MainActivity::class.java);
        startActivity(intent)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity , "Email atau Password Error" , Toast.LENGTH_SHORT).show()
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