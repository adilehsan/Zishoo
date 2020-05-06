package com.ideologer.zamishoapp.ui.fragments


import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.ProductListAdapter
import com.ideologer.zamishoapp.adapters.ProductsAdapter
import com.ideologer.zamishoapp.interfaces.OnFragmentLoaded
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_product_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProductListFragment : Fragment(), ProductListAdapter.onProductItemClick {
    private lateinit var productView: View
    private lateinit var productList: ArrayList<String>
    private var productsAdapter: ProductListAdapter? = null
    private var febMenu: FloatingActionMenu? = null
    private lateinit var toolBar: Toolbar
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        productView =  inflater.inflate(R.layout.fragment_product_list, container, false)
        inIT(productView)
        setToolbar()
        return productView
    }


    private fun inIT(view: View) {
        productList = ArrayList()
        view.rvProductsList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        productsAdapter = ProductListAdapter((activity as HomeActivity), productList, this)
        view.rvProductsList.adapter = productsAdapter
    }

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        febMenu = (activity as HomeActivity).findViewById(R.id.fabMenu)
        febMenu?.visibility = View.GONE
        bottomNavigationView?.visibility = View.GONE
        toolBar.iVBack.visibility = View.VISIBLE
        toolBar.tvBarHeading.text = getString(R.string.for_you)
        toolBar.iVBack.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    override fun onItemClick(int: Int, pId: String) {
        val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment()
        Navigation.findNavController(productView).navigate(action)
    }
}
