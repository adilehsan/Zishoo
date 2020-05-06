package com.ideologer.zamishoapp.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Bitmap
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.asksira.loopingviewpager.LoopingViewPager
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.SlidingPagerAdapter
import com.ideologer.zamishoapp.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_introduction.view.*


/**
 * A simple [Fragment] subclass.
 */
class IntroductionFragment : Fragment(), LoopingViewPager.IndicatorPageChangeListener {
    private lateinit var introView: View
    private lateinit var itemList: ArrayList<Int>
    private var slidingPagerAdapter: SlidingPagerAdapter? = null
    private val imgs = HashMap<String, Bitmap>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        introView = inflater.inflate(R.layout.fragment_introduction, container, false)
        inIT(introView)
        return introView
    }

    private fun inIT(view: View) {
        itemList = ArrayList()
        itemList.add(R.drawable.ic_home_cash)
        itemList.add(R.drawable.ic_home_delivery)
        itemList.add(R.drawable.ic_home_cash_bundle)
        slidingPagerAdapter = SlidingPagerAdapter((activity as MainActivity),itemList,true)
        view.viewPager.adapter = slidingPagerAdapter
        view.viewPager.setIndicatorPageChangeListener(this)
        view.indicator.count = view.viewPager.indicatorCount
        view.btnFreeSignup.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(
                IntroductionFragmentDirections.actionIntroductionFragmentToLoginPhoneFragment(),
                NavOptions.Builder().setPopUpTo(R.id.introductionFragment, true).build()
            )
        })


    }

    override fun onIndicatorProgress(selectingPosition: Int, progress: Float) {

    }

    override fun onIndicatorPageChange(newIndicatorPosition: Int) {
        introView.indicator.selection = newIndicatorPosition
        if (newIndicatorPosition == 0){
            introView.top_text.text = "Earn from Home \n with out Any Investment"
        }else if(newIndicatorPosition == 1){
            introView.top_text.text = "Don't Worry About Delivery \n with very low delivery charges"
        } else if(newIndicatorPosition == 2){
            introView.top_text.text = "Share as much as you can \n and get profit"
        }
    }

    override fun onResume() {
        super.onResume()
        introView.viewPager.resumeAutoScroll()
    }

    override fun onPause() {
        introView.viewPager.pauseAutoScroll()
        super.onPause()
    }
}
