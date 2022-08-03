package com.example.foodmarket.ui.ui.order.inprogress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.BuildConfig
import com.example.foodmarket.R
import com.example.foodmarket.helpers.Helpers.formatPrice
import com.example.foodmarket.model.response.transaction.Data
import kotlinx.android.synthetic.main.item_inprogress.view.*

class InProgressListAdapter(private var foodList : List<Data> , private var itemAdapterClickCallback : ItemAdapterClickCallback) : RecyclerView.Adapter<InProgressListAdapter.InProgressViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):InProgressListAdapter.InProgressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.item_inprogress , parent , false)

        return InProgressViewHolder(mView)
    }

    override fun onBindViewHolder(inProgressViewHolder: InProgressViewHolder, position: Int) {
        inProgressViewHolder.bind(foodList[position],itemAdapterClickCallback)
    }

    override fun getItemCount(): Int = foodList.size

    inner class InProgressViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(transaction: Data, itemAdapterClickCallbak: ItemAdapterClickCallback){


            itemView.apply {

                val imageData = "${BuildConfig.BASE_URL}storage/${transaction.food.picturePath}"

                Glide.with(itemView.context)
                        .load(imageData)
                        .apply(RequestOptions().override(60,60))
                        .into(ivPoster)

                tvTitle.text = transaction.food.name
                tvTotal.text = "${transaction.quantity.toString()} Items"
                tvPrice.formatPrice(transaction.total.toString())

                itemView.setOnClickListener { itemAdapterClickCallbak.onClickFood(transaction) }

            }

        }

    }

    interface ItemAdapterClickCallback{
        fun onClickFood(food: Data)
    }

}