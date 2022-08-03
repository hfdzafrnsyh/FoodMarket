package com.example.foodmarket.ui.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.BuildConfig
import com.example.foodmarket.R
import com.example.foodmarket.helpers.Helpers.formatPrice
import com.example.foodmarket.model.entity.Food
import com.example.foodmarket.model.response.home.Data
import kotlinx.android.synthetic.main.item_home_vertical.view.*


class HomeListAdapter(private var foodList : List<Data>, private var itemAdapterClickCallbak : ItemAdapterClickCallback) : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListAdapter.HomeListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.item_home_vertical , parent , false)

        return HomeListViewHolder(mView)
    }

    override fun onBindViewHolder(homeListViewHolder: HomeListAdapter.HomeListViewHolder, position: Int) {
        homeListViewHolder.bind(foodList[position],itemAdapterClickCallbak)
    }

    override fun getItemCount(): Int = foodList.size

    inner class HomeListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(food: Data , itemAdapterClickCallbak: ItemAdapterClickCallback){


            itemView.apply {

                val imageData = "${BuildConfig.BASE_URL}storage/${food.picturePath}"

                Glide.with(itemView.context)
                    .load(imageData)
                    .apply(RequestOptions().override(60,60))
                    .into(ivPoster)

                tvTitle.text = food.name
                tvPrice.formatPrice(food.price.toString())
                rbFood.rating = food.rating?.toFloat()?: 0f
                tvRating.text = food.rating?.toString()

                itemView.setOnClickListener { itemAdapterClickCallbak.onClickFood(food) }

            }

        }

    }

    interface ItemAdapterClickCallback{
        fun onClickFood(food: Data)
    }
}