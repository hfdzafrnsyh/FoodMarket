package com.example.foodmarket.ui.ui.profile.foodmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmarket.R
import com.example.foodmarket.model.entity.ProfileMenu
import com.example.foodmarket.ui.ui.profile.ProfileListAdapter
import kotlinx.android.synthetic.main.fragment_profile_food_market.*

class ProfileFoodMarketFragment : Fragment() , ProfileListAdapter.ItemAdapterClickCallback {

    private lateinit var profileMenuList : ArrayList<ProfileMenu>
    private lateinit var profileListAdapter : ProfileListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_food_market, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummyMenu()
        showProfileMenuList()
    }


    fun initDummyMenu(){
        profileMenuList = ArrayList()
        profileMenuList.add(ProfileMenu("Rate App"))
        profileMenuList.add(ProfileMenu("Help Center"))
        profileMenuList.add(ProfileMenu("Privacy & Policy"))
        profileMenuList.add(ProfileMenu("Term & Conditions"))
    }

    fun showProfileMenuList(){
        profileListAdapter = ProfileListAdapter(profileMenuList , this)

        rvProfileFoodMarket.setHasFixedSize(true)
        rvProfileFoodMarket.layoutManager = LinearLayoutManager(activity)
        rvProfileFoodMarket.adapter = profileListAdapter
    }

    override fun onClickMenuProfile(profileMenu: ProfileMenu) {
        Toast.makeText(activity , "${profileMenu.title}" , Toast.LENGTH_SHORT).show()
    }

}