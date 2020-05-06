package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ideologer.zamishoapp.R
import com.ideologer.zamishoapp.utils.Constants
import kotlinx.android.synthetic.main.item_collections.view.*

class CollectionsAdapter(val context: Context, val itemList: ArrayList<String>) :
    RecyclerView.Adapter<CollectionsAdapter.CollectionsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsHolder {
        return CollectionsHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_collections,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CollectionsHolder, position: Int) {
        holder.tv_title.typeface = Typeface.createFromAsset(context.getAssets(), Constants.EXTRABOLD)
    }

    class CollectionsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_title = view.tv_title
    }
}