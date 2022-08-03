package com.example.foodmarket.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.R
import com.example.foodmarket.model.request.RegisterRequest
import com.example.foodmarket.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment(), View.OnClickListener {

    private var filePath : Uri? = null
    private lateinit var registerRequest : RegisterRequest

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        btnContinue.setOnClickListener(this)


    }



    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnContinue -> {
                actionRegister(view)
            }
        }
    }

    fun actionRegister(view : View?){
        view?.let {

            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val confirmedPassword = edtConfirmedPassword.text.toString()

            if(name.isNullOrEmpty()){
                edtName.error = "Type Your Name"
                edtName.requestFocus()
            }else if(email.isNullOrEmpty()){
                edtEmail.error = "Type Your Email"
                edtEmail.requestFocus()
            }else if(password.isNullOrEmpty()){
                edtPassword.error = "Type Your Password"
                edtPassword.requestFocus()
            }else if(!password.equals(confirmedPassword)){
                edtConfirmedPassword.error = "Confirm Password must match with password"
                edtConfirmedPassword.requestFocus()
            }else{
                val user = RegisterRequest(
                        name,
                        email,
                        password,
                        confirmedPassword,
                        "" , "" , "" , "",
                        filePath
                        )

                Log.d("DATA_USER" , user.toString())
                var bundle = Bundle()
                bundle.putParcelable(SignUpAddressFragment.DATA_USER , user)
                Navigation.findNavController(it)
                        .navigate(R.id.fragmentSignUpAddress,bundle)
                (activity as AuthActivity).toolbarSignUpAddress();
            }

        }

    }



}