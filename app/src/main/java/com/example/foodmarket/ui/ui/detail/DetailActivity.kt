package com.example.foodmarket.ui.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.foodmarket.R
import com.example.foodmarket.model.response.home.Data
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity : AppCompatActivity() {

    private lateinit var dataFood : Data

    companion object {
        val DATA_FOOD = "DATA_FOOD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        intent.extras?.let {
            var bundle = Bundle()
            bundle.putParcelable("data" , it.get("data") as Parcelable)
            val navController = Navigation.findNavController(findViewById(R.id.nav_host_detail_fragment))
            navController.setGraph(navController.graph , bundle)

        }



    }

    fun toolbarDetail(){
        toolbar.visibility = View.GONE
    }

    fun toolbarPayment(){
        toolbar.visibility = View.VISIBLE
        toolbar.title = "Payment"
        toolbar.subtitle = "Pay and eat"
        toolbar.navigationIcon= resources.getDrawable(R.drawable.ic_baseline_arrow_back_ios_24)
        toolbar.setOnClickListener {
            onBackPressed()
            toolbarDetail()
        }
    }


}