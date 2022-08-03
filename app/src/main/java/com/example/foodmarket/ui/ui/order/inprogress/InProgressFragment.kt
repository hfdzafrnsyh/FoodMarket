package com.example.foodmarket.ui.ui.order.inprogress

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
import com.example.foodmarket.ui.ui.detail.DetailActivity
import com.example.foodmarket.ui.ui.order.detailtransaction.DetailTransactionActivity
import kotlinx.android.synthetic.main.fragment_in_progress.*


class InProgressFragment : Fragment(), InProgressListAdapter.ItemAdapterClickCallback {


    private lateinit var inprogressAdapter : InProgressListAdapter
    private  var inprogressList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_progress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        showCardListAdapter()

    }



    private fun showCardListAdapter(){

        inprogressList = arguments?.getParcelableArrayList("data")!!

        if(!inprogressList.isNullOrEmpty()){
            inprogressAdapter = InProgressListAdapter(inprogressList!!, this)
            rvInProgress.setHasFixedSize(true)
            rvInProgress.layoutManager = LinearLayoutManager(activity)
            rvInProgress.adapter = inprogressAdapter
        }
    }

    override fun onClickFood(food: Data) {

        var intent = Intent(activity , DetailTransactionActivity::class.java)
        intent.putExtra("data" , food)
        startActivity(intent)
    }


}