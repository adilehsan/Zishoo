package com.ideologer.zamishoapp.ui.fragments


import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_my_profile.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class MyProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var profileView: View
    private lateinit var toolBar: Toolbar
    var mYear: Int = 0
    var mMonth: Int = 0
    var genders: ArrayList<String> = ArrayList()
    var mDay: Int = 0
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileView = inflater.inflate(R.layout.fragment_my_profile, container, false)
        inIT(profileView)
        setToolbar()
        // Inflate the layout for this fragment
        return profileView
    }

    private fun inIT(view: View) {
        view.etUserDOB.setOnClickListener(this)
        view.etUserGender.setOnClickListener(this)
        view.etUserProvince.setOnClickListener(this)
        view.etUserCity.setOnClickListener(this)
        getGender()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.etUserDOB -> {
                getDOB()
            }
            R.id.etUserGender -> {
                profileView.etUserGender.requestFocus()
                profileView.etUserGender.showDropDown()
            }
            R.id.etUserProvince -> {
                profileView.etUserProvince.requestFocus()
                profileView.etUserProvince.showDropDown()
            }
            R.id.etUserCity -> {
                profileView.etUserCity.requestFocus()
                profileView.etUserCity.showDropDown()
            }

        }
    }

    private fun getGender() {
        genders.add("Male")
        genders.add("Female")
        val gendersAdapter = ArrayAdapter<String>((activity as HomeActivity), R.layout.item_drop_down_list, genders)
        profileView.etUserGender.threshold = 0 //will start working from first character
        profileView.etUserGender.setAdapter(gendersAdapter)
        val provinceAdapter = ArrayAdapter<String>((activity as HomeActivity), R.layout.item_drop_down_list, Constants.getProvincesList())
        profileView.etUserProvince.threshold = 0 //will start working from first character
        profileView.etUserProvince.setAdapter(provinceAdapter)
        val citiesAdapter = ArrayAdapter<String>((activity as HomeActivity), R.layout.item_drop_down_list, Constants.getCitiesList())
        profileView.etUserCity.threshold = 0 //will start working from first character
        profileView.etUserCity.setAdapter(citiesAdapter)
    }

    private fun getDOB() {
        val c = Calendar.getInstance()
        c.add(Calendar.YEAR, -15)
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            (activity as HomeActivity),
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    view: DatePicker, year: Int,
                    monthOfYear: Int, dayOfMonth: Int
                ) {
                    profileView.etUserDOB.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                }
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        bottomNavigationView?.visibility = View.GONE
        toolBar.iVBack.visibility = View.VISIBLE
        toolBar.tvBarHeading.text = getString(R.string.edit_profile)
        toolBar.iVBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
