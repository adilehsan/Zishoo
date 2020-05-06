package com.ideologer.zamishoapp.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.interfaces.OnFragmentLoaded
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_drawer.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemReselectedListener,BottomNavigationView.OnNavigationItemSelectedListener,OnFragmentLoaded,
    View.OnClickListener {
    lateinit var toolbar: Toolbar
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        inIT()
    }

    private fun inIT() {
        toolbar = findViewById(R.id.toolbar_actionbar)
        navController = Navigation.findNavController(this, R.id.nav_home_fragment)
        fabMenu.setOnMenuButtonClickListener {
            fabMenu.open(true)
            if (!fabMenu.isOpened){
                fabMenu.open(true)
            }else{
                fabMenu.close(true)
            }
        }
//        val actionBarDrawerToggle = ActionBarDrawerToggle(
//            this,
//            drawer_layout,
//            toolbar,
//            R.string.drawer_open,
//            R.string.drawer_close
//        )
//        drawer_layout.addDrawerListener(actionBarDrawerToggle)
//        navigation_view.itemIconTintList = null
        bottomNavigationView = findViewById(R.id.navigationBottom)
        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
        bottomNavigationView?.setOnNavigationItemReselectedListener(this)
        toolbar.tvBarHeading.text = getString(R.string.for_you)
      //  navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
//        drawer_layout.closeDrawers()
        when (menuItem.itemId) {
            R.id.menu_for_you -> {
                toolbar.tvBarHeading.text = getString(R.string.for_you)
                navController.navigate(R.id.forYouFragment)
            }
            R.id.menu_collections -> {
                toolbar.tvBarHeading.text = getString(R.string.collections)
                navController.navigate(R.id.collectionsFragment)
            }
            R.id.menu_orders -> {
                toolbar.tvBarHeading.text = getString(R.string.orders)
                navController.navigate(R.id.ordersFragment)
            }
            R.id.menu_accounts -> {
                toolbar.tvBarHeading.text = getString(R.string.my_account)
                navController.navigate(R.id.myAccountFragment2)
            }
            else -> {
                return true
            }
        }
        return false
    }

    override fun onNavigationItemReselected(p0: MenuItem) {

    }

    override fun onClick(view: View?) {
        when (view?.id) {
        }

    }

    private fun openDrawer() {
        if (drawer_layout.isDrawerVisible(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onFragmentLoaded(drawerItems: Constants.DrawerItems) {
        bottomNavigationView?.selectedItemId = drawerItems.menuItemId
    }

    //    override fun onBackPressed() {
//        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
//            drawer_layout.closeDrawers()
//        } else {
//            super.onBackPressed()
//        }
//
//    }
}