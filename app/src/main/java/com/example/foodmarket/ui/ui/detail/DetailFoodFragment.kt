package com.example.foodmarket.ui.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.BuildConfig
import com.example.foodmarket.R
import com.example.foodmarket.helpers.Helpers.formatPrice
import com.example.foodmarket.model.response.home.Data
import kotlinx.android.synthetic.main.fragment_detail_food.*
import java.lang.Integer.parseInt


class DetailFoodFragment : Fragment(), View.OnClickListener {



    companion object {
        val DATA_FOOD = "DATA_FOOD"
    }

    private lateinit var bundle : Bundle
    private lateinit var dataFood : Data
    var total : Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_food, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//
        (activity as DetailActivity).toolbarDetail()

        btnBackDetail.setOnClickListener(this)
        btnOrderNow.setOnClickListener(this)

        dataFood = requireActivity().intent.getParcelableExtra("data")!!


        showDetailFood(dataFood)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnBackDetail -> {
                (activity as DetailActivity).onBackPressed()
            }
            R.id.btnOrderNow -> {
                Navigation.findNavController(view)
                        .navigate(R.id.fragmentPaymemt , bundle)
                (activity as DetailActivity).toolbarPayment()
            }
        }
    }

    private fun showDetailFood(food : Data?){

        bundle = Bundle()
        bundle.putInt("total" , total)
        bundle.putParcelable("data" , dataFood)


        val imageData = "${BuildConfig.BASE_URL}storage/${food?.picturePath}"
        Glide.with(this)
                .load(imageData)
                .apply(RequestOptions())
                .into(ivPosterFood)

        tvTitle.text = food?.name
        tvDescription.text = food?.description
        tvPrice.formatPrice(food?.price?.toString())
        tvRating.text = food?.rating
        rbRating.rating = food?.rating?.toFloat() ?:0f
        tvIngredients.text = food?.ingredients
        tvTotal.text=total.toString()


        btnCountPlus.setOnClickListener {
            total += 1
            bundle.putInt("total" , total)
            tvTotal.text = total.toString()
        }

        btnCountMin.setOnClickListener {
            if(total <=1){
                total = 1
            }else{
                total -=1
            }
            bundle.putInt("total" , total)
            tvTotal.text = total.toString()
        }




    }



}



