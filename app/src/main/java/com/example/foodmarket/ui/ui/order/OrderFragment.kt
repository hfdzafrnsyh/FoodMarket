package com.example.foodmarket.ui.ui.order

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foodmarket.R
import com.example.foodmarket.model.response.transaction.Data
import com.example.foodmarket.model.response.transaction.TransactionResponse
import kotlinx.android.synthetic.main.fragment_order.*


class OrderFragment : Fragment() , OrderContract.View {

    private lateinit var presenter : OrderPresenter
    private var progressBar : Dialog? = null
    private var inprogressList : ArrayList<Data>? = ArrayList()
    private  var pastOrderList : ArrayList<Data>? = ArrayList()
    private lateinit var toolbar : Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_order, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        initViewLoading()
        presenter = OrderPresenter(this)
        presenter.getTransaction()


    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {

        if(transactionResponse.data.isNullOrEmpty()){
            ll_empty.visibility = View.VISIBLE
            cl_TabOrder.visibility = View.GONE
            include.visibility = View.GONE
        }else{

            for( i in transactionResponse.data.indices){
                if(transactionResponse.data[i].status.equals("ON_DELIVERY" , true)){
                    inprogressList?.add(transactionResponse.data[i])
                }else if(transactionResponse.data[i].status.equals("DELIVERED" , true)
                        || transactionResponse.data[i].status.equals("CANCELLED", true)
                        || transactionResponse.data[i].status.equals("SUCCESS", true)){
                    pastOrderList?.add(transactionResponse.data[i])
                }
            }

            var sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
                sectionPagerAdapter.setData(inprogressList,pastOrderList)
                vpOrder.adapter = sectionPagerAdapter
                tlOrder.setupWithViewPager(vpOrder)
        }

    }

    override fun onTransactionFailed(message: String) {
      Toast.makeText(activity , message , Toast.LENGTH_SHORT).show()
    }


    fun initViewLoading(){
        progressBar = Dialog(requireContext())
        val progressBarLayout = layoutInflater.inflate(R.layout.dialog_progress_bar , null)

        progressBar?.let {
            it.setContentView(progressBarLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)!!
        toolbar.title = "Your Orders";
        toolbar.subtitle="Wait for the best meals";

    }

    override fun showLoading() {
       progressBar?.show()
    }

    override fun dismissLoading() {
        progressBar?.dismiss()
    }
}