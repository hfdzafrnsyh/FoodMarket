package com.example.foodmarket.ui.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmarket.R
import com.example.foodmarket.model.entity.Food
import com.example.foodmarket.model.response.home.Data
import com.example.foodmarket.ui.ui.detail.DetailActivity
import com.example.foodmarket.ui.ui.home.HomeListAdapter
import kotlinx.android.synthetic.main.fragment_home_recommen.*


class HomeRecommenFragment : Fragment() , HomeListAdapter.ItemAdapterClickCallback {


    private lateinit var foodList : ArrayList<Food>
    private lateinit var homeListAdapter : HomeListAdapter
    private var newTasteList : ArrayList<Data>? = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_recommen, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showCardListAdapter()
    }


    fun showCardListAdapter(){


        newTasteList = arguments?.getParcelableArrayList("data")

        Log.d("data recommended" , newTasteList.toString())

        homeListAdapter = HomeListAdapter(newTasteList!!, this)
        rvRecommended.setHasFixedSize(true)
        rvRecommended.layoutManager = LinearLayoutManager(activity)
        rvRecommended.adapter = homeListAdapter

    }


    override fun onClickFood(food: Data) {
        val intent = Intent(activity , DetailActivity::class.java)
        intent.putExtra("data" , food)
        startActivity(intent)
    }
}