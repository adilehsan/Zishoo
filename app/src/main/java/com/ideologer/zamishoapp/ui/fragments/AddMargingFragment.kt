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
import kotlinx.android.synthetic.main.fragment_add_marging.view.*

/**
 * A simple [Fragment] subclass.
 */
class AddMargingFragment : Fragment() {
    private var toolBar: Toolbar? = null
    private var febMenu: FloatingActionMenu? = null
    private lateinit var marginView: View
    private lateinit var cartItemList: ArrayList<String>
    private var cartAdapter: CartAdapter? = null
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        marginView = inflater.inflate(R.layout.fragment_add_marging, container, false)
        inIT(marginView)
        setToolbar()
        // Inflate the layout for this fragment
        return marginView
    }
    private fun inIT(view: View){
        cartItemList = ArrayList()
        view.rvCart.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        cartAdapter = CartAdapter((activity as HomeActivity), cartItemList)
        view.rvCart.adapter = cartAdapter
        view.btnDone.setOnClickListener(View.OnClickListener {
            val action = AddMargingFragmentDirections.actionAddMargingFragmentToSelectCustomerFragment()
            Navigation.findNavController(it).navigate(action)
        })
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
        toolBar?.tvBarHeading?.text = getString(R.string.add_margin)
        toolBar?.iVBack?.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}
