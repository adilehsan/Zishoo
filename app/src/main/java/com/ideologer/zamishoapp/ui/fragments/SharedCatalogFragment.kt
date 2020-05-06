package com.ideologer.zamishoapp.ui.fragments


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.adapters.SharedCatalogAdapter
import com.ideologer.zamishoapp.ui.activity.HomeActivity
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.bottom_sheet_share.*
import kotlinx.android.synthetic.main.bottom_sheet_share.view.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_shared_catalog.view.*
import java.io.File
import java.io.IOException
import java.net.URL

/**
 * A simple [Fragment] subclass.
 */
class SharedCatalogFragment : Fragment(), SharedCatalogAdapter.onProductItemClick,
    SharedCatalogAdapter.onShareItemClick {

    private lateinit var sharedView: View
    private lateinit var productList: ArrayList<String>
    private var productsAdapter: SharedCatalogAdapter? = null
    private var bottomSheet: LinearLayout? = null
    private lateinit var toolBar: Toolbar
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedView = inflater.inflate(R.layout.fragment_shared_catalog, container, false)
        setToolbar()
        inIT(sharedView)
        // Inflate the layout for this fragment
        return sharedView
    }

    private fun inIT(view: View) {
        productList = ArrayList()
        bottomSheet = view.bottom_sheet
        view.rvSharedCatalog.layoutManager = GridLayoutManager(activity, 2)
        productsAdapter = SharedCatalogAdapter((activity as HomeActivity), productList, this, this)
        view.rvSharedCatalog.adapter = productsAdapter
    }

    override fun onItemClick(int: Int, pId: String) {
        val action =
            SharedCatalogFragmentDirections.actionSharedCatalogFragmentToProductDetailFragment()
        Navigation.findNavController(sharedView).navigate(action)
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

    private fun setToolbar() {
        toolBar = (activity as HomeActivity).findViewById(R.id.toolbar_actionbar)
        bottomNavigationView = (activity as HomeActivity).findViewById(R.id.navigationBottom)
        bottomNavigationView?.visibility = View.GONE
        toolBar.iVBack.visibility = View.VISIBLE
        toolBar.tvBarHeading.text = getString(R.string.shared_catalog)
        toolBar.iVBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
