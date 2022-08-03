package com.example.foodmarket.ui.ui.detail.payment

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.BuildConfig
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.helpers.Helpers.formatPrice
import com.example.foodmarket.model.request.CheckoutRequest
import com.example.foodmarket.model.response.checkout.CheckoutResponse
import com.example.foodmarket.model.response.home.Data
import com.example.foodmarket.model.response.login.User
import com.example.foodmarket.ui.ui.detail.DetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*


class PaymentFragment : Fragment(), PaymentContract.View, View.OnClickListener {

    private lateinit var dataFood : Data
    private lateinit var user : User
    private var progressBar : Dialog? = null
    private lateinit var presenter : PaymentPresenter
    private lateinit var checkoutRequest: CheckoutRequest

    private var total : Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataFood = arguments?.getParcelable<Data>("data")!!
        total = arguments?.getInt("total")!!

        val gson = Gson()
        user = gson.fromJson(FoodMarket.getApp().getUser() , User::class.java)

        presenter = PaymentPresenter(this)


        initViewPayment(dataFood, total!!, user)
        initViewLoading()

        btnCheckOutNow.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnCheckOutNow -> {
               actionCheckout(view,total!!)
            }
        }
    }

    private fun initViewPayment(food : Data?,total:Int , user : User){
        var imageData = "${BuildConfig.BASE_URL}storage/${food?.picturePath}"

        Glide.with(requireContext())
                .load(imageData)
                .apply(RequestOptions().override(60,60))
                .into(ivPosterFood)

        tvName.text = food?.name
        tvPrice.formatPrice(food?.price.toString())
        tvTotalItem.text = "${total} Items"
        tvNameDetail.text = food?.name


        var totalHarga = food?.price!! * total
        tvPriceDetail.formatPrice(totalHarga.toString())

        var priceDriver = 20000
        tvPriceDriver.formatPrice(priceDriver.toString())

        var tax = totalHarga * 10 / 100
        tvPriceTax.formatPrice(tax.toString())


        var paymentTotal = totalHarga + priceDriver + tax
        tvTotalPayment.formatPrice(paymentTotal.toString())

//        user
        tvNameUser.text = user.name
        tvAddresUser.text = user.address
        tvHouseUser.text = user.number_home.toString()
        tvPhoneUser.text = user.number_phone.toString()
        tvCityUser.text = user.city


    }


    private fun actionCheckout(view : View,total : Int){
        view?.let{
            var harga = dataFood?.price!! * total
            var totalPayment = harga + 20000 + harga.div(10)

            checkoutRequest = CheckoutRequest(
                    user?.id.toString(),
                    dataFood?.id.toString(),
                    total.toString(),
                    totalPayment.toString(),
                    "ON_DELIVERY"
                )

            presenter.getCheckout(checkoutRequest,it)
        }
    }

    private fun initViewLoading(){
        progressBar = Dialog(requireContext())
        val progressBarLayout = layoutInflater.inflate(R.layout.dialog_progress_bar , null)

        progressBar?.let {
            it.setContentView(progressBarLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {

        (activity as DetailActivity).toolbarDetail()
        Navigation.findNavController(view)
                .navigate(R.id.fragmentPaymemtSuccess , null)

        var intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(intent)




    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(activity , "Checkout Failed" , Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBar?.show()
    }

    override fun dismissLoading() {
        progressBar?.dismiss()
    }

}