package com.example.foodmarket.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.foodmarket.R

class AuthActivity : AppCompatActivity() {

    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val pageRequest = intent.getIntExtra("PAGE_REQUEST",0)

        if(pageRequest == 2 ){
            toolbarSignUp()
            val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.fragmentSignIn,true)
                    .build()

            Navigation.findNavController(findViewById(R.id.authNavHost))
                    .navigate(R.id.fragmentSignUp , null , navOptions)
        }
    }


    fun toolbarSignUp(){
        toolbar = findViewById<Toolbar>(R.id.toolbar);
        toolbar.title = "Sign Up";
        toolbar.subtitle="Register and eat";
        toolbar.navigationIcon= resources.getDrawable(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpAddress(){
        toolbar = findViewById<Toolbar>(R.id.toolbar);
        toolbar.title = "Address";
        toolbar.subtitle="Make sure it's valid";
        toolbar.navigationIcon= resources.getDrawable(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpSuccess(){
        toolbar = findViewById<Toolbar>(R.id.toolbar);
        toolbar.visibility = View.GONE;
    }
}