package com.example.foodmarket.ui.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.ui.ui.profile.account.ProfileAccountFragment
import com.example.foodmarket.ui.ui.profile.foodmarket.ProfileFoodMarketFragment

class SectionPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Profile"
            1 -> "FoodMarket"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var mFragment : Fragment

        when(position){
            0 -> {
                mFragment = ProfileAccountFragment()
                return mFragment
            }
            1 -> {
                mFragment = ProfileFoodMarketFragment()
                return mFragment
            }

            else -> {
                mFragment = ProfileAccountFragment()
                return mFragment
            }
        }
    }

}