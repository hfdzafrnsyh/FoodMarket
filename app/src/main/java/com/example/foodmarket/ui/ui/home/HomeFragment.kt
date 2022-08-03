package com.example.foodmarket.ui.ui.home


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import kotlinx.android.synthetic.main.fragment_home.*
import com.example.foodmarket.model.response.home.Data
import com.example.foodmarket.model.response.home.HomeResponse
import com.example.foodmarket.model.response.login.User
import com.example.foodmarket.ui.ui.detail.DetailActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_home.ivProfile
import kotlinx.android.synthetic.main.fragment_profile.*

class HomeFragment : Fragment(), HomeCardAdapter.ItemAdapterClickCalbback,HomeContract.View ,View.OnClickListener {

    private lateinit var homeCardAdapter: HomeCardAdapter
    private lateinit var sectionPagerAdapter: SectionPagerAdapter
    private lateinit var presenter : HomePresenter
    private var progressBar : Dialog? = null

    private  var newTasteList : ArrayList<Data>? = ArrayList()
    private  var recommendedList : ArrayList<Data>? = ArrayList()
    private  var popularList : ArrayList<Data>? = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        initViewLoading()
        showPhotoProfileUser()

        presenter = HomePresenter(this)
        presenter.getHome()


        ivProfile.setOnClickListener(this)

    }




    override fun clickFood(food: Data) {
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("data" , food)
        startActivity(intent)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.ivProfile -> {
               Navigation.findNavController(view)
                       .navigate(R.id.navigation_profile , null)
            }
        }
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {

        showHomeCardHorizontal(homeResponse)
        showPagerAdapter(homeResponse)

    }

    fun showPagerAdapter(homeResponse: HomeResponse){


        for( i in homeResponse.data.indices){

            var items : List<String> = homeResponse.data[i].type?.split(",") ?:ArrayList()

            for( j in items.indices){
                if( items[j].equals("new taste" , true)){
                    newTasteList?.add(homeResponse.data[i])
                }else if(items[j].equals("popular" , true)){
                    popularList?.add(homeResponse.data[i])
                }else if(items[j].equals("recommended" , true)){
                    recommendedList?.add(homeResponse.data[i])
                }
            }

        }

        sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        sectionPagerAdapter.setData(newTasteList,popularList,recommendedList)
        vpHome.adapter = sectionPagerAdapter
        tlHome.setupWithViewPager(vpHome)
    }


    fun showHomeCardHorizontal(homeResponse : HomeResponse){
        homeCardAdapter = HomeCardAdapter(homeResponse.data ,this)

        rvCardList.setHasFixedSize(true)
        rvCardList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
        rvCardList.adapter = homeCardAdapter

    }


    override fun onHomeFailed(message: String) {
        Toast.makeText(activity , "${message}" , Toast.LENGTH_SHORT).show()

    }

    fun initViewLoading(){
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

    fun showPhotoProfileUser(){

        val gson = Gson()
        val users  = gson.fromJson(FoodMarket.getApp().getUser(), User::class.java)

        if(users.profile_photo_path != null) {
            Glide.with(this)
                    .load(users.profile_photo_url)
                    .apply(RequestOptions().override(110, 110).circleCrop())
                    .into(ivProfile)
        }

    }

}

