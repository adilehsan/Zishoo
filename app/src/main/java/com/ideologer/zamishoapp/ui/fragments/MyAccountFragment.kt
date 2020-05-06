package com.ideologer.zamishoapp.ui.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.interfaces.OnFragmentLoaded
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_my_account.view.*

/**
 * A simple [Fragment] subclass.
 */
class MyAccountFragment : Fragment(), View.OnClickListener {
    private lateinit var myAccountView: View
    private lateinit var toolBar: Toolbar
    private var bottomNavigationView: BottomNavigationView? = null
    private var onFragmentLoaded: OnFragmentLoaded? = null
    private var febMenu: FloatingActionMenu? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myAccountView = inflater.inflate(R.layout.fragment_my_account, container, false)
        onFragmentLoaded?.onFragmentLoaded(Constants.DrawerItems.ACCOUNT)
        // Inflate the layout for this fragment
        inIT(myAccountView)
        setToolbar()
        return myAccountView
    }

    private fun inIT(view: View) {

        view.layout_image.setOnClickListener(this)
        view.layout_payments.setOnClickListener(this)
        view.layout_shared_catalog.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.layout_image -> {
                val action =
                    MyAccountFragmentDirections.actionMyAccountFragment2ToMyProfileFragment()
                Navigation.findNavController(view).navigate(action)
            }
            R.id.layout_payments -> {
                val action =
                    MyAccountFragmentDirections.actionMyAccountFragment2ToPaymentHistoryFragment()
                Navigation.findNavController(view).navigate(action)
            }
            R.id.layout_shared_catalog -> {
                val action =
                    MyAccountFragmentDirections.actionMyAccountFragment2ToSharedCatalogFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        febMenu = (activity as HomeActivity).findViewById(R.id.fabMenu)
        febMenu?.visibility = View.VISIBLE
        bottomNavigationView?.visibility = View.VISIBLE
        toolBar.iVBack.visibility = View.GONE
        toolBar.tvBarHeading.text = getString(R.string.my_account)

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
