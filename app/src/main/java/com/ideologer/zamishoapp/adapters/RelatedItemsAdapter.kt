package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ideologer.zamishoapp.R

class RelatedItemsAdapter(
    val context: Context,
    val itemList: ArrayList<String>
) : RecyclerView.Adapter<RelatedItemsAdapter.RelatedItemsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedItemsHolder {
        return RelatedItemsHolder(
            LayoutInflater.from(context).inflate(
                R.layout.items_related_product,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: RelatedItemsHolder, position: Int) {

    }

    class RelatedItemsHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}