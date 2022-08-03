package com.example.foodmarket.ui.ui.detail.payment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.foodmarket.R
import com.example.foodmarket.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_payment_success.*


class PaymentSuccessFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_success, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnOrderOther.setOnClickListener(this)
        btnViewMyOrder.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnOrderOther -> {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finishAffinity()
            }
            R.id.btnViewMyOrder -> {
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("PAGE_REQUEST" , 1)
                startActivity(intent)
                activity?.finishAffinity()
            }
        }
    }

}