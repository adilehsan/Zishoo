package com.ideologer.zamishoapp.ui.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.ui.activity.MainActivity
import com.ideologer.zamishoapp.ui.dialogues.CustomLoadingDialogue
import com.ideologer.zamishoapp.utils.AppSettings
import kotlinx.android.synthetic.main.dialogue_verification_success.*

class SplashFragment : Fragment() {
    private lateinit var splashView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        splashView = inflater.inflate(R.layout.fragment_splash, container, false)
        Handler().postDelayed({
            splashView.let {
                if (AppSettings.getUserLogin()){
                    startActivity(Intent(activity, HomeActivity::class.java))
                    activity?.finish()
                }else{
                    Navigation.findNavController(it).navigate(SplashFragmentDirections.actionSplashFragmentToIntroductionFragment()
                        , NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build())
                }

            }
        },2000)
        return splashView

    }

}
