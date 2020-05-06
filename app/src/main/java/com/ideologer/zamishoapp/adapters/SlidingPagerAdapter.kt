package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.ideologer.zamishoapp.R

import java.util.ArrayList

class SlidingPagerAdapter(context: Context, itemList: ArrayList<Int>, isInfinite: Boolean) :
    LoopingPagerAdapter<Int>(context, itemList, isInfinite) {
    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_view_pager, container, false)
    }

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val imageView = convertView.findViewById<ImageView>(R.id.ivPager)
        imageView.setImageResource(itemList[listPosition])
    }
}
