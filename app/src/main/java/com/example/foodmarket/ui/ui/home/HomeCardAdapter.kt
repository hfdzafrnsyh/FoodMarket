package com.example.foodmarket.ui.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.BuildConfig
import com.example.foodmarket.R
import com.example.foodmarket.model.response.home.Data
import kotlinx.android.synthetic.main.item_home_horizontal.view.*

class HomeCardAdapter(private val listFood: List<Data>, private val itemAdapterClickCallback: ItemAdapterClickCalbback) : RecyclerView.Adapter<HomeCardAdapter.HomeCardViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal , parent , false)
        return HomeCardViewHolder(view)
    }

    override fun onBindViewHolder(homeCardViewHolder: HomeCardViewHolder, position: Int) {
      homeCardViewHolder.bind(listFood[position] , itemAdapterClickCallback)
    }

    override fun getItemCount(): Int = listFood.size

    inner class HomeCardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {



        fun bind(food : Data , itemAdapterClickCallback: ItemAdapterClickCalbback){

            itemView.apply {
                val imageData = "${BuildConfig.BASE_URL}storage/${food.picturePath}"
                Glide.with(itemView.context)
                        .load(imageData)
                        .apply(RequestOptions().override(200,140))
                        .into(ivPoster)
                tvTitle.text = food.name
                rbFood.rating = food.rating?.toFloat() ?:0f
                tvRating.text = food.rating?.toString()

                itemView.setOnClickListener { itemAdapterClickCallback.clickFood(food) }
            }

        }

    }


    interface ItemAdapterClickCalbback{
        fun clickFood(food : Data)
    }



}