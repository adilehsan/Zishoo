package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ideologer.zamishoapp.R
import kotlinx.android.synthetic.main.item_product_list.view.*
import kotlinx.android.synthetic.main.item_product_list.view.ivProduct
import kotlinx.android.synthetic.main.item_product_list.view.tv_shipp_off
import kotlinx.android.synthetic.main.item_products.view.*

class ProductListAdapter (val context: Context,
                          val itemList: ArrayList<String>,
                          val onProductClick: onProductItemClick) : RecyclerView.Adapter<ProductListAdapter.ProductListHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListHolder {
        return ProductListHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_product_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return 8
    }

    override fun onBindViewHolder(holder: ProductListHolder, position: Int) {
        holder.productIV.setOnClickListener {
            onProductClick.onItemClick(position, "")
        }
        holder.tvOffShipping.paintFlags = holder.tvOffShipping.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    class ProductListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productIV = view.ivProduct
        val layoutWhatsapp = view.layout_whatsapp
        val layoutOthers = view.layout_others
        val tvOffShipping = view.tv_shipp_off
    }
    interface onProductItemClick {
        fun onItemClick(int: Int, pId: String)
    }
}