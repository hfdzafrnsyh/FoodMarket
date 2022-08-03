package com.example.foodmarket.ui.ui.profile.account.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.example.foodmarket.R

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val navController = Navigation.findNavController(this,R.id.nav_host_edit_profile)
            navController.setGraph(navController.graph,null)

    }
}