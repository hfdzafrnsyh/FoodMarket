package com.example.foodmarket.ui.ui.profile.account

import android.content.Intent
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
import com.example.foodmarket.ui.ui.profile.account.editprofile.EditProfileActivity
import kotlinx.android.synthetic.main.fragment_profile_account.*


class ProfileAccountFragment : Fragment(), ProfileListAdapter.ItemAdapterClickCallback {


    private lateinit var profileListAdapter: ProfileListAdapter
    private lateinit var profileMenuList : ArrayList<ProfileMenu>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_account, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummyMenu()
        showProfileMenuList()
    }


    fun initDummyMenu(){
        profileMenuList = ArrayList()
        profileMenuList.add(ProfileMenu("Edit Profile"))
        profileMenuList.add(ProfileMenu("Home Address"))
        profileMenuList.add(ProfileMenu("Security"))
        profileMenuList.add(ProfileMenu("Payment"))
    }

    fun showProfileMenuList(){
        profileListAdapter = ProfileListAdapter(profileMenuList , this)

        rvProfileAcount.setHasFixedSize(true)
        rvProfileAcount.layoutManager = LinearLayoutManager(activity)
        rvProfileAcount.adapter = profileListAdapter
    }

    override fun onClickMenuProfile(profileMenu: ProfileMenu) {

        if(profileMenu.title.equals("Edit Profile" , true)){
            val intent = Intent(activity ,EditProfileActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(activity , "${profileMenu.title}" , Toast.LENGTH_SHORT).show()
        }

    }


}