package com.ideologer.zamishoapp.ui.fragments


import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.UserAddressesAdapter
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_select_customer.view.*

/**
 * A simple [Fragment] subclass.
 */
class SelectCustomerFragment : Fragment() {
    var dialog: Dialog? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var addressList: ArrayList<String>
    private var addressesAdapter: UserAddressesAdapter? = null
    private var toolBar: Toolbar? = null
    private var febMenu: FloatingActionMenu? = null
    private lateinit var screenView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        screenView = inflater.inflate(R.layout.fragment_select_customer, container, false)
        inIT(screenView)
        return screenView
    }

    private fun inIT(view: View){
        view.btnNewAddress.setOnClickListener {
            addNewAddressDialogue()
        }
        addressList = ArrayList()
        view.rvAddresses.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        addressesAdapter = UserAddressesAdapter((activity as HomeActivity), addressList)
        view.rvAddresses.adapter = addressesAdapter
    }

    private fun addNewAddressDialogue(){

        activity?.let {
            dialog = Dialog(it,android.R.style.Theme_Light_NoTitleBar_Fullscreen)
        }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimationUpDown
        dialog?.setCancelable(true)
        dialog?.setContentView(R.layout.add_new_address)
        dialog?.show()
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
        toolBar?.tvBarHeading?.text = getString(R.string.select_address)
        toolBar?.iVBack?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
