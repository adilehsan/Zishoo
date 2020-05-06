package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ideologer.zamishoapp.R
import kotlinx.android.synthetic.main.item_products.view.*
import kotlin.collections.ArrayList
import com.ideologer.zamishoapp.ui.activity.MainActivity
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import java.net.URL
import androidx.core.content.ContextCompat.startActivity
import com.ideologer.zamishoapp.utils.Constants
import java.net.MalformedURLException
import java.net.URISyntaxException


class ProductsAdapter(
    val context: Context,
    val itemList: ArrayList<String>,
    val onProductClick: onProductItemClick,
    val onShareClick: onShareItemClick
) :
    RecyclerView.Adapter<ProductsAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_products,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.productIV.setOnClickListener {
            onProductClick.onItemClick(position, "")
        }
        holder.tvOffShipping.paintFlags = holder.tvOffShipping.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.layoutOthers.setOnClickListener {
            val imageUriArray = ArrayList<URL>()
            imageUriArray.add(URL("https://i.picsum.photos/id/237/200/300.jpg"))
            imageUriArray.add(URL("https://homepages.cae.wisc.edu/~ece533/images/airplane.png"))
            imageUriArray.add(URL("https://homepages.cae.wisc.edu/~ece533/images/cat.png"))
            onShareClick.onShareClick(imageUriArray, false, "")
        }
        holder.layoutWhatsapp.setOnClickListener {
            val imageUriArray = ArrayList<URL>()
            imageUriArray.add(URL("https://i.picsum.photos/id/237/200/300.jpg"))
            imageUriArray.add(URL("https://homepages.cae.wisc.edu/~ece533/images/airplane.png"))
            imageUriArray.add(URL("https://homepages.cae.wisc.edu/~ece533/images/cat.png"))
//           try {
//               val url1 = URL("https://i.picsum.photos/id/237/200/300.jpg")
//               val url2 = URL("https://i.picsum.photos/id/237/200/300.jpg")
//               val url3 = URL("https://i.picsum.photos/id/237/200/300.jpg")
//               val uri1 = Uri.parse(url1.toURI().toString())
//               val uri2 = Uri.parse(url2.toURI().toString())
//               val uri3 = Uri.parse(url3.toURI().toString())
//
//               imageUriArray.add(uri1)
//               imageUriArray.add(uri2)
//               imageUriArray.add(uri3)
//           }catch (e: MalformedURLException){
//               e.printStackTrace()
//           }catch (ex: URISyntaxException){
//               ex.printStackTrace()
//           }

            onShareClick.onShareClick(imageUriArray, true, "")

//            val intent = Intent()
//            intent.action = Intent.ACTION_SEND_MULTIPLE
//            intent.putExtra(Intent.EXTRA_TEXT, "Text caption message!!")
//            intent.type = "text/plain"
//            intent.type = "*/*"
//            //intent.setPackage("com.whatsapp")
//            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUriArray)
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            context.startActivity(Intent.createChooser(intent, "Share Image!"))


        }
    }

    class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productIV = view.ivProduct
        val layoutWhatsapp = view.layout_whatsapp
        val layoutOthers = view.layout_others
        val tvOffShipping = view.tv_shipp_off
    }

    interface onProductItemClick {
        fun onItemClick(int: Int, pId: String)
    }

    interface onShareItemClick {
        fun onShareClick(urlList: ArrayList<URL>, isWhatsapp: Boolean, pId: String)
    }
}