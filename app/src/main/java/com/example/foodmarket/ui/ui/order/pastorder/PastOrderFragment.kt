package com.example.foodmarket.ui.ui.order.pastorder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmarket.R
import com.example.foodmarket.model.response.transaction.Data
import com.example.foodmarket.ui.ui.order.detailtransaction.DetailTransactionActivity
import kotlinx.android.synthetic.main.fragment_past_order.*


class PastOrderFragment : Fragment(), PastOrderListAdapter.ItemAdapterClickCallback {

    private lateinit var pastOrderAdapter : PastOrderListAdapter
    private var pastOrderList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        showCardListAdapter()
    }


    private fun showCardListAdapter(){
        pastOrderList = arguments?.getParcelableArrayList("data")!!



        if(!pastOrderList.isNullOrEmpty()){
            pastOrderAdapter = PastOrderListAdapter(pastOrderList!!,this)
            rvPastOrder.setHasFixedSize(true)
            rvPastOrder.layoutManager = LinearLayoutManager(activity)
            rvPastOrder.adapter = pastOrderAdapter
        }
    }

    override fun onClickFood(food: Data) {
        var intent = Intent(activity , DetailTransactionActivity::class.java)
        intent.putExtra("data" , food)
        startActivity(intent)
    }

}