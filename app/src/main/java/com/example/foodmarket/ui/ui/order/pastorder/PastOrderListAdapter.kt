package com.example.foodmarket.ui.ui.order.pastorder

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.BuildConfig
import com.example.foodmarket.R
import com.example.foodmarket.helpers.Helpers.convertISOTimeToDate
import com.example.foodmarket.helpers.Helpers.formatDate
import com.example.foodmarket.helpers.Helpers.formatPrice
import com.example.foodmarket.model.response.transaction.Data
import kotlinx.android.synthetic.main.item_pastorder.view.*


class PastOrderListAdapter(private var foodList : List<Data>, private var itemAdapterClickCallback : ItemAdapterClickCallback) : RecyclerView.Adapter<PastOrderListAdapter.PastOrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PastOrderListAdapter.PastOrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mView = layoutInflater.inflate(R.layout.item_pastorder , parent , false)

        return PastOrderViewHolder(mView)
    }

    override fun onBindViewHolder(pastOrderViewHolder: PastOrderViewHolder, position: Int) {
        pastOrderViewHolder.bind(foodList[position],itemAdapterClickCallback)
    }

    override fun getItemCount(): Int = foodList.size

    inner class PastOrderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(transaction: Data, itemAdapterClickCallbak: ItemAdapterClickCallback){


            itemView.apply {

                val imageData = "${BuildConfig.BASE_URL}storage/${transaction.food.picturePath}"

                Glide.with(itemView.context)
                        .load(imageData)
                        .apply(RequestOptions().override(60,60))
                        .into(ivPoster)

                tvTitle.text = transaction.food.name
                tvTotal.text = "${transaction.quantity} Items"
                tvPrice.formatPrice(transaction.total.toString())
                tvStatus.text = transaction.status
                tvDate.formatDate(transaction.createdAt)

//                Log.d("date" ,date.toString())

                if(transaction.status.equals("CANCELLED" , true)){
                    tvStatus.visibility = View.VISIBLE
                    tvStatus.setTextColor(Color.parseColor("#dc143c"))

                }else if(transaction.status.equals("DELIVERED" , true)){
                    tvStatus.visibility = View.VISIBLE
                    tvStatus.setTextColor(Color.parseColor("#1ABC9C"))

                }

                itemView.setOnClickListener { itemAdapterClickCallbak.onClickFood(transaction) }

            }

        }

    }

    interface ItemAdapterClickCallback{
        fun onClickFood(food: Data)
    }

}