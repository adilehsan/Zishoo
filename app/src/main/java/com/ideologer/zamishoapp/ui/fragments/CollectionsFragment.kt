package com.ideologer.zamishoapp.ui.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asksira.loopingviewpager.LoopingViewPager
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.CollectionsAdapter
import com.ideologer.zamishoapp.adapters.SlidingPagerAdapter
import com.ideologer.zamishoapp.interfaces.OnFragmentLoaded
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_collections.view.*

/**
 * A simple [Fragment] subclass.
 */
class CollectionsFragment : Fragment(), LoopingViewPager.IndicatorPageChangeListener {
    private lateinit var collectionView: View
    private lateinit var bannersList: ArrayList<Int>
    private lateinit var collectionList: ArrayList<String>
    private lateinit var toolBar: Toolbar
    private var febMenu: FloatingActionMenu? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private var collectionsAdapter: CollectionsAdapter? = null
    private var onFragmentLoaded: OnFragmentLoaded? = null
    private var slidingPagerAdapter: SlidingPagerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        collectionView = inflater.inflate(R.layout.fragment_collections, container, false)
        onFragmentLoaded?.onFragmentLoaded(Constants.DrawerItems.COLLECTIONS)
        inIT(collectionView)
        setToolbar()
        // Inflate the layout for this fragment
        return collectionView
    }

    private fun inIT(view: View) {
        bannersList = ArrayList()
        collectionList = ArrayList()
        bannersList.add(R.drawable.banner_one)
        bannersList.add(R.drawable.banner_two)
        bannersList.add(R.drawable.banner_three)
        slidingPagerAdapter = SlidingPagerAdapter((activity as HomeActivity), bannersList, true)
        view.vpBanners.adapter = slidingPagerAdapter
        view.vpBanners.setIndicatorPageChangeListener(this)
        view.indicator.count = view.vpBanners.indicatorCount
        view.rvCollections.layoutManager = GridLayoutManager(activity,2)
        collectionsAdapter  = CollectionsAdapter((activity as HomeActivity),collectionList)
        view.rvCollections.adapter = collectionsAdapter

    }

    override fun onIndicatorProgress(selectingPosition: Int, progress: Float) {

    }

    override fun onIndicatorPageChange(newIndicatorPosition: Int) {
        collectionView.indicator.selection = newIndicatorPosition
    }

    override fun onResume() {
        super.onResume()
        collectionView.vpBanners.resumeAutoScroll()
    }

    override fun onPause() {
        collectionView.vpBanners.pauseAutoScroll()
        super.onPause()
    }
    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        febMenu = (activity as HomeActivity).findViewById(R.id.fabMenu)
        febMenu?.visibility = View.VISIBLE
        bottomNavigationView?.visibility = View.VISIBLE
        toolBar.iVBack.visibility = View.GONE
        toolBar.tvBarHeading.text = getString(R.string.collections)

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
