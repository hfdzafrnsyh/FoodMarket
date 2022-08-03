package com.example.foodmarket.ui.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.model.entity.ProfileMenu
import kotlinx.android.synthetic.main.item_profile_account_list.view.*

class ProfileListAdapter(private val profileMenu : ArrayList<ProfileMenu> , private val itemAdapterClickCallback : ItemAdapterClickCallback) : RecyclerView.Adapter<ProfileListAdapter.ProfileListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileListViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        val mView =   layoutInflater.inflate(R.layout.item_profile_account_list , parent,false)
        return ProfileListViewHolder(mView)
    }

    override fun onBindViewHolder(profileListViewHolder: ProfileListViewHolder, position: Int) {
        profileListViewHolder.bind(profileMenu[position],itemAdapterClickCallback)
    }

    override fun getItemCount(): Int = profileMenu.size



    inner class ProfileListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(profileMenu: ProfileMenu , itemAdapterClickCallback: ItemAdapterClickCallback){

            itemView.apply {

                tvProfileAccount.text = profileMenu.title

                itemView.setOnClickListener { itemAdapterClickCallback.onClickMenuProfile(profileMenu) }

            }

        }

    }


    interface ItemAdapterClickCallback{
        fun onClickMenuProfile(profileMenu: ProfileMenu)
    }



}