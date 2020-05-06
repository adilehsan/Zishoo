package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.ideologer.zamishoapp.R
import kotlinx.android.synthetic.main.item_products.view.*
import java.net.URL

class SharedCatalogAdapter(
    val context: Context,
    val itemList: ArrayList<String>,
    val onProductClick: onProductItemClick,
    val onShareClick: onShareItemClick
) : RecyclerView.Adapter<SharedCatalogAdapter.SharedItemsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedItemsHolder {
        return SharedItemsHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_products,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: SharedItemsHolder, position: Int) {
        holder.productIV.setOnClickListener {
            onProductClick.onItemClick(position, "")
        }
        holder.shareTv.text = "Reshare On"
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

            onShareClick.onShareClick(imageUriArray, true, "")

        }
    }

    class SharedItemsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productIV = view.ivProduct
        val layoutWhatsapp = view.layout_whatsapp
        val layoutOthers = view.layout_others
        val tvOffShipping = view.tv_shipp_off
        val shareTv = view.tvShare
    }

    interface onProductItemClick {
        fun onItemClick(int: Int, pId: String)
    }

    interface onShareItemClick {
        fun onShareClick(urlList: ArrayList<URL>, isWhatsapp: Boolean, pId: String)
    }
}
