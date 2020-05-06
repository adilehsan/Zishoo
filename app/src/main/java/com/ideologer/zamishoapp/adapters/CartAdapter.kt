package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ideologer.zamishoapp.R

class CartAdapter(val context: Context,
                  val itemList: ArrayList<String>) : RecyclerView.Adapter<CartAdapter.CartHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_cart,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {

    }

    class CartHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
