package com.example.foodmarket.ui.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.foodmarket.model.response.transaction.Data
import com.example.foodmarket.ui.ui.order.inprogress.InProgressFragment
import com.example.foodmarket.ui.ui.order.pastorder.PastOrderFragment


class SectionPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var inprogressList : ArrayList<Data>? = ArrayList()
    private var pastOrderList : ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "In Progress"
            1 -> "Past Order"
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
                mFragment = InProgressFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data" , inprogressList)
                mFragment.arguments = bundle
                return mFragment
            }
            1 -> {
                mFragment = PastOrderFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data" , pastOrderList)
                mFragment.arguments = bundle
                return mFragment
            }

            else -> {
                mFragment = InProgressFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data" , inprogressList)
                mFragment.arguments = bundle
                return mFragment
            }
        }
    }

    fun setData(inprogresssParams : ArrayList<Data>? ,pastOrderParams : ArrayList<Data>? ){
        inprogressList = inprogresssParams
        pastOrderList = pastOrderParams
    }

}