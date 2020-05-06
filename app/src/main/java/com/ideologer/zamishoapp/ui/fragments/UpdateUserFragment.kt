package com.ideologer.zamishoapp.ui.fragments


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ideologer.zamishoapp.dto.request.UpdateUserRequest
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.ui.activity.MainActivity
import com.ideologer.zamishoapp.utils.AppSettings
import com.ideologer.zamishoapp.utils.Constants
import com.ideologer.zamishoapp.viewModels.UpdateUserViewModel
import kotlinx.android.synthetic.main.fragment_update_user.view.*
import kotlinx.android.synthetic.main.fragment_update_user.view.etUserEmail
import java.util.*
import kotlin.collections.ArrayList
import android.app.Dialog
import com.ideologer.zamishoapp.R


/**
 * A simple [Fragment] subclass.
 */
class UpdateUserFragment : Fragment(), View.OnClickListener {
    private lateinit var profileView: View
    var mYear: Int = 0
    var mMonth: Int = 0
    private var viewModel: UpdateUserViewModel? = null
    var genders: ArrayList<String> = ArrayList()
    var mDay: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileView = inflater.inflate(R.layout.fragment_update_user, container, false)
        // Inflate the layout for this fragment
        inIT(profileView)
        return profileView
    }

    private fun inIT(view: View) {
        viewModel =
            ViewModelProviders.of(this).get(UpdateUserViewModel::class.java)
        view.etUserDOB.setOnClickListener(this)
        view.btnUpdate.setOnClickListener(this)
        view.tvSkip.setOnClickListener(this)
        getGender()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.etUserDOB -> {
                getDOB()
            }
            R.id.tvSkip -> {
                startActivity(Intent(activity, HomeActivity::class.java))
                activity?.finish()
            }
            R.id.btnUpdate -> {
                updateUser()
            }
        }
    }

    private fun updateUser() {
        if (profileView.etUserName.text.toString().isEmpty()) {
            profileView.etUserName.error = getString(R.string.name_error)
        } else if (profileView.etUserEmail.text.toString().isEmpty()) {
            profileView.etUserEmail.error = getString(R.string.emil_error)
        } else if (profileView.etUserAddress.text.toString().isEmpty()) {
            profileView.etUserAddress.error = getString(R.string.address_error)
        } else if (profileView.etUserGender.text.toString().isEmpty()) {
            profileView.etUserGender.error = getString(R.string.gender_error)
        } else if (profileView.etUserDOB.text.toString().isEmpty()) {
            profileView.etUserDOB.error = getString(R.string.birth_error)
        } else if (profileView.etUserCity.text.toString().isEmpty()) {
            profileView.etUserCity.error = getString(R.string.city_error)
        } else if (profileView.etUserProvince.text.toString().isEmpty()) {
            profileView.etUserProvince.error = getString(R.string.province_error)
        } else {
            val splited = profileView.etUserName.text.split(" ")
            var firstName = ""
            var lastName = ""
            if (splited.size > 1) {
                firstName = splited[0]
                lastName = splited[1]
            }else{
                firstName = profileView.etUserName.text.toString()
            }
            viewModel?.inIT(
                AppSettings.getUserToken(), UpdateUserRequest(
                    AppSettings.getUserNumber(),
                    firstName,
                    lastName,
                    profileView.etUserCity.text.toString(),
                    profileView.etUserProvince.text.toString(),
                    profileView.etUserAddress.text.toString(),
                    profileView.etPostalCode.text.toString(),
                    profileView.etUserEmail.text.toString(),
                    profileView.etUserGender.text.toString(),
                    profileView.etUserDOB.text.toString()
                ).body
            )
            viewModel?.updateUserLiveData()?.observe((activity as MainActivity),androidx.lifecycle.Observer {
                if (it == null) {
                    Toast.makeText(activity, "some thing wrong", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
                startActivity(Intent(activity, HomeActivity::class.java))
                activity?.finish()
            })
        }

    }



    private fun getGender() {
        genders.add("Male")
        genders.add("Female")
        val gendersAdapter = ArrayAdapter<String>(
            (activity as MainActivity),
            R.layout.item_drop_down_list,
            genders
        )
        profileView.etUserGender.threshold = 0 //will start working from first character
        profileView.etUserGender.setAdapter(gendersAdapter)
        profileView.etUserGender.setOnClickListener(View.OnClickListener {
            profileView.etUserGender.requestFocus()
            profileView.etUserGender.showDropDown()
        })
        val provinceAdapter = ArrayAdapter<String>((activity as MainActivity), R.layout.item_drop_down_list, Constants.getProvincesList())
        profileView.etUserProvince.threshold = 0 //will start working from first character
        profileView.etUserProvince.setAdapter(provinceAdapter)
        profileView.etUserProvince.setOnClickListener(View.OnClickListener {
            profileView.etUserProvince.requestFocus()
            profileView.etUserProvince.showDropDown()
        })
        val citiesAdapter = ArrayAdapter<String>((activity as MainActivity), R.layout.item_drop_down_list, Constants.getCitiesList())
        profileView.etUserCity.threshold = 2 //will start working from first character
        profileView.etUserCity.setAdapter(citiesAdapter)
    }

    private fun getDOB() {
        val c = Calendar.getInstance()
        c.add(Calendar.YEAR, -15)
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            (activity as MainActivity),
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

}
