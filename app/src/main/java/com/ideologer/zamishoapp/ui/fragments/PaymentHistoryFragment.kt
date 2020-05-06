package com.ideologer.zamishoapp.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.PaymentHistoryAdapter
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_payment_history.view.*

/**
 * A simple [Fragment] subclass.
 */
class PaymentHistoryFragment : Fragment() {
    private lateinit var paymentView: View
    private lateinit var toolBar: Toolbar
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var productList: ArrayList<String>
    private var paymentsAdapter: PaymentHistoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        paymentView = inflater.inflate(R.layout.fragment_payment_history, container, false)
        // Inflate the layout for this fragment
        inIT(paymentView)
        setToolbar()
        return paymentView
    }

    private fun inIT(view: View) {
        productList = ArrayList()
        view.rvPayments.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        paymentsAdapter = PaymentHistoryAdapter((activity as HomeActivity), productList)
        view.rvPayments.adapter = paymentsAdapter
    }

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        bottomNavigationView?.visibility = View.GONE
        toolBar.iVBack.visibility = View.VISIBLE
        toolBar.tvBarHeading.text = getString(R.string.payment_history)
        toolBar.iVBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}
