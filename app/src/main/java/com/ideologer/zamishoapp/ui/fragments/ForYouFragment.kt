package com.ideologer.zamishoapp.ui.fragments


import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.ProductsAdapter
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_for_you.view.*
import android.graphics.Bitmap
import java.net.URL
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.github.clans.fab.FloatingActionMenu
import com.ideologer.zamishoapp.BuildConfig
import com.ideologer.zamishoapp.interfaces.OnFragmentLoaded
import com.ideologer.zamishoapp.ui.activity.MainActivity
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.bottom_sheet_share.*
import kotlinx.android.synthetic.main.bottom_sheet_share.view.*
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class ForYouFragment : Fragment(), ProductsAdapter.onProductItemClick,
    ProductsAdapter.onShareItemClick {
    private lateinit var forYouView: View
    private lateinit var productList: ArrayList<String>
    private var productsAdapter: ProductsAdapter? = null
    private var bottomSheet: LinearLayout? = null
    private var febMenu: FloatingActionMenu? = null
    private lateinit var toolBar: Toolbar
    private var onFragmentLoaded: OnFragmentLoaded? = null
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        forYouView = inflater.inflate(R.layout.fragment_for_you, container, false)
        // Inflate the layout for this fragment
        onFragmentLoaded?.onFragmentLoaded(Constants.DrawerItems.PRODUCTS)
        inIT(forYouView)
        setToolbar()
        return forYouView
    }

    private fun inIT(view: View) {

        productList = ArrayList()
        bottomSheet = view.bottom_sheet
        view.rvProducts.layoutManager = GridLayoutManager(activity, 2)
        productsAdapter = ProductsAdapter((activity as HomeActivity), productList, this, this)
        view.rvProducts.adapter = productsAdapter
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        febMenu = (activity as HomeActivity).findViewById(R.id.fabMenu)
        febMenu?.visibility = View.VISIBLE
        bottomNavigationView?.visibility = View.VISIBLE
        toolBar.iVBack.visibility = View.GONE
        toolBar.tvBarHeading.text = getString(R.string.for_you)
        toolBar.tvBarHeading.typeface = Typeface.createFromAsset(activity?.assets, Constants.ITALIC)

    }

    override fun onItemClick(int: Int, pId: String) {
        val action = ForYouFragmentDirections.actionForYouFragmentToProductListFragment()
        Navigation.findNavController(forYouView).navigate(action)
    }

    override fun onShareClick(urlList: ArrayList<URL>, isWhatsapp: Boolean, pId: String) {
        if (isWhatsapp) {
            val uriList = ArrayList<Uri>()
            val clipboard =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", "this the text you want to share")
            if (clipboard == null || clip == null) return
            clipboard.setPrimaryClip(clip)
            Thread(Runnable {
                try {
                    File(activity?.cacheDir, "images").deleteOnExit()
                    for (x in urlList.indices) {
                        val requestOptions = RequestOptions()
                        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL).dontTransform()
                        Glide.with((activity as HomeActivity))
                            .asBitmap()
                            .apply(requestOptions)
                            .load(urlList.get(x))
                            .listener(object : RequestListener<Bitmap> {
                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    return false
                                }

                                override fun onResourceReady(
                                    resource: Bitmap?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    uriList.add(
                                        Constants.getImageUri(
                                            (activity as HomeActivity),
                                            resource
                                        )
                                    )
                                    if (uriList.size == urlList.size) {
                                        val intent = Intent()
                                        intent.action = Intent.ACTION_SEND_MULTIPLE
                                        intent.putExtra(Intent.EXTRA_TEXT, "Text caption message!!")
                                        intent.putParcelableArrayListExtra(
                                            Intent.EXTRA_STREAM,
                                            uriList
                                        )
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        intent.type = "image/*"
                                        intent.setPackage("com.whatsapp")
                                        startActivity(intent)

                                    }
                                    return true
                                }
                            }).submit()

                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }).start()
        } else {
            shareDialogue()
        }

    }

//    private fun getImageBitmap(url: String): Bitmap? {
//        var bm: Bitmap? = null
//        try {
//            val aURL = URL(url)
//            val conn = aURL.openConnection()
//            conn.connect()
//            val `is` = conn.getInputStream()
//            val bis = BufferedInputStream(`is`)
//            bm = BitmapFactory.decodeStream(bis)
//            bis.close()
//            `is`.close()
//        } catch (e: IOException) {
//            Log.e(TAG, "Error getting bitmap", e)
//        }
//
//        return bm
//    }


    private fun shareDialogue() {
        val mBottomSheetDialog = RoundedBottomSheetDialog((activity as HomeActivity))
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_share, null)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
        mBottomSheetDialog.layout_fb.setOnClickListener {
            mBottomSheetDialog.dismiss()
        }
        mBottomSheetDialog.layout_insta.setOnClickListener {
            mBottomSheetDialog.dismiss()
        }
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

    val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            (activity as HomeActivity).finish()
        }
    }
}
