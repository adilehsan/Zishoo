package com.ideologer.zamishoapp.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.CartAdapter
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_cart.view.*

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment(), View.OnClickListener {
    private var toolBar: Toolbar? = null
    private var febMenu: FloatingActionMenu? = null
    private lateinit var cartView: View
    private lateinit var cartItemList: ArrayList<String>
    private var cartAdapter: CartAdapter? = null
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartView = inflater.inflate(R.layout.fragment_cart, container, false)
        inIT(cartView)
        setToolbar()
        // Inflate the layout for this fragment
        return cartView
    }
    private fun inIT(view: View){
        cartItemList = ArrayList()
        view.rvCart.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        cartAdapter = CartAdapter((activity as HomeActivity), cartItemList)
        view.rvCart.adapter = cartAdapter
        view.btnNext.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNext -> {
                val action = CartFragmentDirections.actionCartFragmentToAddMargingFragment()
                Navigation.findNavController(cartView).navigate(action)
            }
        }
    }

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        febMenu = (activity as HomeActivity).findViewById(R.id.fabMenu)
        febMenu?.visibility = View.GONE
        bottomNavigationView?.visibility = View.GONE
        toolBar?.iVBack?.visibility = View.VISIBLE
        toolBar?.iVSearch?.visibility = View.GONE
        toolBar?.iVCart?.visibility = View.GONE
        toolBar?.tvBarHeading?.text = getString(R.string.shop_cart)
        toolBar?.iVBack?.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}
