package com.example.foodmarket.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodmarket.R
import com.example.foodmarket.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_sign_up_success.*


class SignUpSuccessFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_success, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnSignUpSuccess.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnSignUpSuccess -> {
                val intent = Intent(activity , MainActivity::class.java)
                startActivity(intent)
                activity?.finishAffinity()
            }
        }
    }

}