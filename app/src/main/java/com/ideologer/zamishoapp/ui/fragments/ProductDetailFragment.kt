package com.ideologer.zamishoapp.ui.fragments


import android.graphics.Paint
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.asksira.loopingviewpager.LoopingViewPager
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.ProductsAdapter
import com.ideologer.zamishoapp.adapters.RelatedItemsAdapter
import com.ideologer.zamishoapp.adapters.SlidingPagerAdapter
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.ui.activity.MainActivity
import kotlinx.android.synthetic.main.bottom_sheet_add_cart.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_product_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailFragment : Fragment(), LoopingViewPager.IndicatorPageChangeListener,
    View.OnClickListener {

    private lateinit var itemList: ArrayList<Int>
    private var slidingPagerAdapter: SlidingPagerAdapter? = null
    private lateinit var toolBar: Toolbar
    private var febMenu: FloatingActionMenu? = null
    private var sizesList: ArrayList<String> = ArrayList()
    private var colorsList: ArrayList<String> = ArrayList()
    private var productList: ArrayList<String> = ArrayList()
    private var productsAdapter: RelatedItemsAdapter? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var detailView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailView = inflater.inflate(R.layout.fragment_product_detail, container, false)
        // Inflate the layout for this fragment
        inIT(detailView)
        setToolbar()
        return detailView
    }

    private fun inIT(view: View) {
        itemList = ArrayList()
        sizesList.add("S")
        sizesList.add("M")
        sizesList.add("L")
        sizesList.add("XL")
        sizesList.add("XXL")
        colorsList.add("Black")
        colorsList.add("Blue")
        colorsList.add("grey")
        colorsList.add("white")
        itemList.add(R.drawable.produc_image_one)
        itemList.add(R.drawable.product_image)
        itemList.add(R.drawable.produc_image_two)
        slidingPagerAdapter = SlidingPagerAdapter((activity as HomeActivity), itemList, true)
        view.vpImages.adapter = slidingPagerAdapter
        view.vpImages.setIndicatorPageChangeListener(this)
        view.layout_add_cart.setOnClickListener(this)
        view.indicator.count = view.vpImages.indicatorCount
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val lpColors = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lpColors.setMargins(5, 0, 5, 0)
        lp.setMargins(10, 0, 10, 0)
        for (x in sizesList.indices) {
            val textView = TextView((activity as HomeActivity))
            textView.text = sizesList.get(x)
            //   textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14)
            textView.setLayoutParams(lp)
            textView.setPadding(20, 2, 20, 2)
            textView.setBackgroundResource(R.drawable.sizes_item_corner)
            textView.setTextColor(getResources().getColor(R.color.black_color))
            detailView.layout_sizes.addView(textView)
        }
        view.tv_shipping_off.paintFlags = view.tv_shipping.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

//        for (x in colorsList.indices) {
//            val textView = TextView((activity as HomeActivity))
//            textView.text = colorsList.get(x)
//            //   textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14)
//            textView.setLayoutParams(lp)
//            textView.setPadding(7, 5, 7, 5)
//            textView.setBackgroundResource(R.drawable.sizes_item_corner)
//            textView.setTextColor(getResources().getColor(R.color.black_color))
//            detailView.layout_colors.addView(textView)
//        }
    }

    override fun onIndicatorProgress(selectingPosition: Int, progress: Float) {

    }

    override fun onIndicatorPageChange(newIndicatorPosition: Int) {
        detailView.indicator.selection = newIndicatorPosition
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.layout_add_cart -> {
                openAddCartSheet()
            }
        }
    }

    private fun openAddCartSheet() {
        var item_count = 0
        val mBottomSheetDialog = RoundedBottomSheetDialog((activity as HomeActivity))
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_add_cart, null)
        mBottomSheetDialog.setContentView(sheetView)
        val gendersAdapter = ArrayAdapter<String>(
            (activity as HomeActivity),
            R.layout.item_drop_down_list,
            sizesList
        )
        mBottomSheetDialog.tvSize.threshold = 0 //will start working from first character
        mBottomSheetDialog.tvSize.setAdapter(gendersAdapter)
        mBottomSheetDialog.tvSize.setOnClickListener(View.OnClickListener {
            mBottomSheetDialog.tvSize.requestFocus()
            mBottomSheetDialog.tvSize.showDropDown()
        })
        mBottomSheetDialog.btnAddCart.setOnClickListener {
            mBottomSheetDialog.dismiss()
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
            Navigation.findNavController(detailView).navigate(action)
        }
        mBottomSheetDialog.tv_plus.setOnClickListener {
            item_count++
            mBottomSheetDialog.tv_count.text = item_count.toString()
        }
        mBottomSheetDialog.tv_minus.setOnClickListener {
            if (item_count == 0) {
                mBottomSheetDialog.tv_count.text = item_count.toString()
            } else {
                item_count--
                mBottomSheetDialog.tv_count.text = item_count.toString()
            }
        }
        mBottomSheetDialog.show()
    }

    override fun onResume() {
        super.onResume()
        detailView.vpImages.resumeAutoScroll()
    }

    override fun onPause() {
        detailView.vpImages.pauseAutoScroll()
        super.onPause()
    }

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        febMenu = (activity as HomeActivity).findViewById(R.id.fabMenu)
        febMenu?.visibility = View.GONE
        bottomNavigationView?.visibility = View.GONE
        toolBar.iVBack.visibility = View.VISIBLE
        toolBar.iVSearch?.visibility = View.VISIBLE
        toolBar.iVCart?.visibility = View.VISIBLE
        toolBar.tvBarHeading.text = getString(R.string.for_you)
        toolBar.iVBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
