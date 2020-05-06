package com.ideologer.zamishoapp.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ideologer.zamishoapp.R
import kotlinx.android.synthetic.main.fragment_login_phone.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginPhoneFragment : Fragment() {
    private lateinit var loginView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        loginView = inflater.inflate(R.layout.fragment_login_phone, container, false)
        inIT(loginView)
        // Inflate the layout for this fragment
        return loginView
    }

    private fun inIT(view: View) {
        view.btnLogin.setOnClickListener(View.OnClickListener {
            if (view.etNumber.text.toString().isEmpty()) {
                view.etNumber.error = "Enter Phone number"
            } else if (view.etNumber.text.toString().length < 11) {
                view.etNumber.error = "Enter Valid Phone number"
            } else {
                val action =
                    LoginPhoneFragmentDirections.actionLoginPhoneFragmentToOtpVerificationFragment()
                var contactNumber = view.etNumber.text.toString()
                contactNumber = "+92" + contactNumber.substring(1)
                action.mobileNumber = contactNumber
                Navigation.findNavController(it)
                    .navigate(action)
            }

        })

    }
}
