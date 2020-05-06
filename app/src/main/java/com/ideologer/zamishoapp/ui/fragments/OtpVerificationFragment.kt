package com.ideologer.zamishoapp.ui.fragments


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_otp_verification.view.*
import com.google.firebase.auth.FirebaseAuth
import com.ideologer.zamishoapp.R
import com.google.firebase.auth.PhoneAuthProvider
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import com.ideologer.zamishoapp.ui.activity.MainActivity
import com.google.android.gms.tasks.TaskExecutors
import com.ideologer.zamishoapp.ui.dialogues.CustomLoadingDialogue
import java.util.concurrent.TimeUnit
import com.ideologer.zamishoapp.dto.request.RegisterUserRequest
import com.ideologer.zamishoapp.utils.AppSettings
import com.ideologer.zamishoapp.viewModels.RegisterUserViewModel
import kotlinx.android.synthetic.main.dialogue_verification_success.*


class OtpVerificationFragment : Fragment() {
    private var args: OtpVerificationFragmentArgs? = null
    private lateinit var otpView: View
    private lateinit var mVerificationId: String
    private var registerViewModel: RegisterUserViewModel? = null
    private var progressDialog: CustomLoadingDialogue? = null
    private var phoneNumber: String = ""
    //firebase auth object
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        otpView = inflater.inflate(R.layout.fragment_otp_verification, container, false)
        progressDialog = CustomLoadingDialogue(activity)
        inIT(otpView)
        // Inflate the layout for this fragment
        return otpView
    }

    private fun inIT(view: View) {
        mAuth = FirebaseAuth.getInstance()
        registerViewModel = ViewModelProviders.of(this).get(RegisterUserViewModel::class.java)
        args = arguments?.let {
            OtpVerificationFragmentArgs.fromBundle(it)
        }
        args?.let {
            phoneNumber = it.mobileNumber
            view.tvMobileNumber.text = phoneNumber
        }
       // sendVerificationCode(phoneNumber)
        otpView.btnLogin.setOnClickListener(View.OnClickListener {
//            if (view.etCode.text.toString().isNotEmpty()) {
//                verifyVerificationCode(view.etCode.text.toString())
//            } else {
//                view.etCode.error = "please enter verification code"
//            }
            val action = OtpVerificationFragmentDirections.actionOtpVerificationFragmentToUpdateUserFragment()
            Navigation.findNavController(otpView).navigate(action)
//            showProgressDialoge()
//            registerUser(phoneNumber)

        })
    }

    private fun sendVerificationCode(mobile: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mobile,
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallbacks
        )
    }

    private val mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

            //Getting the code sent by SMS
            val code = phoneAuthCredential.smsCode

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                otpView.etCode.setText(code)
                //verifying the code
                verifyVerificationCode(code)
            } else {
                hideProgressDialoge()
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }

        override fun onCodeSent(
            s: String,
            forceResendingToken: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(s, forceResendingToken)

            //storing the verification id that is sent to the user
            mVerificationId = s
        }
    }

    private fun verifyVerificationCode(code: String) {
        //creating the credential
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)

        //signing the user
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener((activity as MainActivity),
                OnCompleteListener<AuthResult> { task ->
                    hideProgressDialoge()
                    if (task.isSuccessful) {

                     showDialogue()
                        //verification successful we will start the profile activity


                    } else {

                        //verification unsuccessful.. display an error message

                        var message = "Somthing is wrong, we will fix it soon..."

                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            message = "Invalid code entered..."
                        }
                        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                    }
                })
    }

    private fun showDialogue(){
        val successDialogue =
            Dialog((activity as MainActivity), android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar)
        successDialogue.requestWindowFeature(Window.FEATURE_NO_TITLE)
        successDialogue.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        successDialogue.window?.attributes?.windowAnimations = R.style.DialogAnimationUpDown
        successDialogue.window?.setBackgroundDrawableResource(R.color.colorBlackSixtyPercentageOpacity)
        successDialogue.setCancelable(false)
        successDialogue.setContentView(R.layout.dialogue_verification_success)
        successDialogue.btnProceed.setOnClickListener {
            successDialogue.hide()
           // showProgressDialoge()
            registerUser(phoneNumber)

        }
        successDialogue.show()
    }
    private fun registerUser(phone_number: String) {
        registerViewModel?.inIT(RegisterUserRequest(phone_number).body)
        registerViewModel?.registerUserLiveData()?.observe((activity as MainActivity), Observer {
          hideProgressDialoge()
            if (it != null) {
                AppSettings.setUserLogin(true)
                AppSettings.setUserNumber(phone_number)
                AppSettings.setUserToken("Bearer " + it.userToken)
                val action = OtpVerificationFragmentDirections.actionOtpVerificationFragmentToUpdateUserFragment()
                Navigation.findNavController(otpView).navigate(action)
            } else {
                Toast.makeText(activity, "some thing wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showProgressDialoge() {
        progressDialog?.show()

    }

    private fun hideProgressDialoge() {
        progressDialog?.hide()
    }
}
