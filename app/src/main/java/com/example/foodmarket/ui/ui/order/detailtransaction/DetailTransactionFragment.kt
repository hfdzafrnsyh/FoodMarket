package com.example.foodmarket.ui.ui.order.detailtransaction

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.BuildConfig
import com.example.foodmarket.R
import com.example.foodmarket.helpers.Helpers.formatPrice
import com.example.foodmarket.model.response.transaction.Data
import com.example.foodmarket.ui.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_detail_transaction.*



class DetailTransactionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_transaction, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

      var dataTransaction = requireActivity().intent.getParcelableExtra<Data>("data")!!

        showDetailTransactionView(dataTransaction)
    }


    private fun showDetailTransactionView(data : Data){
        var imageData = "${BuildConfig.BASE_URL}storage/${data?.food.picturePath}"

        Glide.with(requireContext())
                .load(imageData)
                .apply(RequestOptions().override(60,60))
                .into(ivPosterFood)

        tvName.text = data?.food.name
        tvPrice.formatPrice(data?.food.price.toString())
        tvTotalItem.text = "${data.quantity} Items"
        tvNameDetail.text = data?.food.name


        var totalHarga = data?.food?.price * data.quantity
        tvPriceDetail.formatPrice(totalHarga.toString())

        var priceDriver = 20000
        tvPriceDriver.formatPrice(priceDriver.toString())

        var tax = totalHarga * 10 / 100
        tvPriceTax.formatPrice(tax.toString())

        tvTotalPayment.formatPrice(data?.total.toString())

//        user
        tvNameUser.text = data?.user.name
        tvAddresUser.text = data?.user.address
        tvHouseUser.text = data?.user.numberHome.toString()
        tvPhoneUser.text = data?.user.numberPhone.toString()
        tvCityUser.text = data?.user.city



        tvStatus.text = data?.status

        if(data?.status.equals("CANCELLED" , true)){
            tvStatus.setTextColor(Color.parseColor("#dc143c"))

        }else if(data?.status.equals("DELIVERED" , true)){
            tvStatus.setTextColor(Color.parseColor("#1ABC9C"))
        }else if(data?.status.equals("ON_DELIVERY" , true)){
            tvStatus.setTextColor(Color.parseColor("#1976D2"))
        }

        btnCheckFood.setOnClickListener {

            var dataDetail = com.example.foodmarket.model.response.home.Data(
                    data?.food.createdAt,
                    data?.food.description,
                    data?.food.id,
                    data?.food.ingredients,
                    data?.food.name,
                    data?.food.picturePath,
                    data?.food.price,
                    data?.food.rating,
                    data?.food.type,
                    data?.food.updatedAt,
            )

            val intent = Intent(activity , DetailActivity::class.java)
            intent.putExtra("data" , dataDetail)
            startActivity(intent)
        }

    }
}