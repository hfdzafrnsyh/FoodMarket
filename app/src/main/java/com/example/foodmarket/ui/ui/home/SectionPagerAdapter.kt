package com.example.foodmarket.ui.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.foodmarket.model.response.home.Data
import com.example.foodmarket.ui.ui.home.newtaste.HomeNewTasteFragment
import com.example.foodmarket.ui.ui.home.popular.HomePopularFragment
import com.example.foodmarket.ui.ui.home.recommended.HomeRecommenFragment

class SectionPagerAdapter(fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager){

    private var newTasteList : ArrayList<Data>? = ArrayList()
    private var recommendedList : ArrayList<Data>? = ArrayList()
    private var popularList : ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "New Taste"
            1 -> "Popular"
            2 -> "Recommended"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return  3
    }

    override fun getItem(position: Int): Fragment {
        var mfragment : Fragment

        return when(position){
            0 -> {
                mfragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data" , newTasteList)
                mfragment.arguments = bundle
                return  mfragment
            }
            1 -> {
                mfragment = HomePopularFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data" , popularList)
                mfragment.arguments = bundle
                return mfragment
            }
            2 -> {
                mfragment = HomeRecommenFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data" , recommendedList)
                mfragment.arguments = bundle
                return  mfragment
            }
            else -> {
                mfragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data" , newTasteList)
                mfragment.arguments = bundle
                return  mfragment
            }
        }
    }


    fun setData(newTasteParams : ArrayList<Data>?,popularParams : ArrayList<Data>?,recomParams : ArrayList<Data>?){
        newTasteList = newTasteParams
        popularList = popularParams
        recommendedList = recomParams
    }
}