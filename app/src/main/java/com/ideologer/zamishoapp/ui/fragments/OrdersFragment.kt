package com.ideologer.zamishoapp.ui.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.interfaces.OnFragmentLoaded
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.custom_toolbar.view.*

/**
 * A simple [Fragment] subclass.
 */
class OrdersFragment : Fragment() {
    private lateinit var toolBar: Toolbar
    private var febMenu: FloatingActionMenu? = null
    private lateinit var ordersView: View
    private var onFragmentLoaded: OnFragmentLoaded? = null
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ordersView = inflater.inflate(R.layout.fragment_orders, container, false)
        setToolbar()
        onFragmentLoaded?.onFragmentLoaded(Constants.DrawerItems.ORDERS)
        // Inflate the layout for this fragment
        return ordersView
    }

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        febMenu = (activity as HomeActivity).findViewById(R.id.fabMenu)
        febMenu?.visibility = View.VISIBLE
        bottomNavigationView?.visibility = View.VISIBLE
        toolBar.iVBack.visibility = View.GONE
        toolBar.tvBarHeading.text = getString(R.string.orders)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentLoaded) {
            onFragmentLoaded = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        onFragmentLoaded = null
    }

}
