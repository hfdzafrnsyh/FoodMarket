package com.example.foodmarket.ui.ui.order.detailtransaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toolbar
import androidx.navigation.Navigation
import com.example.foodmarket.R
import com.example.foodmarket.model.response.transaction.Data

class DetailTransactionActivity : AppCompatActivity() {


    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaction)

        var data = intent?.getParcelableExtra<Data>("data")

        var bundle = Bundle()
        bundle.putParcelable("data" , data as Parcelable)
        val navController = Navigation.findNavController(findViewById(R.id.nav_detail_transaction))
        navController.setGraph(navController.graph , bundle)


    }


}