package com.example.foodmarket.ui.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_home_new_taste.*

class HomeNewTasteFragment : Fragment(), HomeListAdapter.ItemAdapterClickCallback {

    private lateinit var foodList : ArrayList<Food>
    private lateinit var homeListAdapter : HomeListAdapter
    private var newTasteList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showCardListAdapter()
    }



    fun showCardListAdapter(){

        newTasteList = arguments?.getParcelableArrayList("data")

        homeListAdapter = HomeListAdapter(newTasteList!!, this)
        rvNewTaste.setHasFixedSize(true)
        rvNewTaste.layoutManager = LinearLayoutManager(activity)
        rvNewTaste.adapter = homeListAdapter

    }


    override fun onClickFood(food: Data) {

        val intent = Intent(activity , DetailActivity::class.java)
        intent.putExtra("data" , food)
        startActivity(intent)
    }

}