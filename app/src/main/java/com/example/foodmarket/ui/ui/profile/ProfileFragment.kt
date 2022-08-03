package com.example.foodmarket.ui.ui.profile

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.model.response.login.User
import com.example.foodmarket.model.response.logout.LogoutResponse
import com.example.foodmarket.model.response.updateprofile.UpdateProfileResponse
import com.example.foodmarket.ui.MainActivity
import com.example.foodmarket.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ProfileContract.View ,View.OnClickListener {

    private lateinit var sectionPagerAdapter: SectionPagerAdapter
    private var profilePhotoPath: Uri? = null
    private var progressBar:Dialog?=null
    private lateinit var presenterProfile: ProfilePresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        presenterProfile = ProfilePresenter(this)

        showPagerAdapterProfile()
        showProfileUser()
        initViewLoading()
        ivProfile.setOnClickListener(this)
        btnLogout.setOnClickListener(this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){

            profilePhotoPath = data?.data

            Glide.with(this)
                    .load(profilePhotoPath)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivProfile)

            profilePhotoPath?.let { presenterProfile.submitPhoto(it) }

        }else if(requestCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(activity , ImagePicker.getError(data) , Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity , "Task Canceled" , Toast.LENGTH_SHORT).show()
        }
    }

    fun showPagerAdapterProfile(){
        sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        vpProfile.adapter = sectionPagerAdapter
        tlProfile.setupWithViewPager(vpProfile)
    }

    fun editPhotoProfile(){
        ImagePicker.with(this)
                .cameraOnly()
                .start()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.ivProfile -> {
                editPhotoProfile()
            }
            R.id.btnLogout -> {
                actionLogout(view)
            }
        }
    }

    private fun showProfileUser(){
        val gson = Gson()
        val users = gson.fromJson(FoodMarket.getApp().getUser(),User::class.java)


        if(users.profile_photo_path != null) {
            Glide.with(this)
                    .load(users.profile_photo_url)
                    .apply(RequestOptions().override(110, 110).circleCrop())
                    .into(ivProfile)
        }

        tvName.text = users.name
        tvEmail.text = users.email

    }




    override fun onUpdatePhotoSuccess(updateProfileResponse: UpdateProfileResponse) {
        val gson = Gson()
        val users = gson.toJson(updateProfileResponse.user)
        FoodMarket.getApp().setUser(users)


        val intent = Intent(activity,MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onUpdatePhotoFailed(message: String) {
        Toast.makeText(activity,"Failed update Photo" , Toast.LENGTH_SHORT).show()
    }


    private fun actionLogout(view:View){
        presenterProfile.submitLogout(view)
    }

    override fun onLogoutSuccess(logoutResponse: LogoutResponse,view : View) {
        FoodMarket.getApp().setUser("")
        FoodMarket.getApp().setToken("")

        if(FoodMarket.getApp().getToken().isNullOrEmpty()){
            var intent = Intent(activity , AuthActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun onLogoutFailed(mesage: String) {
        Toast.makeText(activity,mesage , Toast.LENGTH_SHORT).show()
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



    override fun showLoading() {
       progressBar?.show()
    }

    override fun dismissLoading() {
        progressBar?.dismiss()
    }

}