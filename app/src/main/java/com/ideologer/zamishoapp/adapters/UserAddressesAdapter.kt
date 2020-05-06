package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ideologer.zamishoapp.R
import kotlinx.android.synthetic.main.item_user_address.view.*

class UserAddressesAdapter(val context: Context,val itemList: ArrayList<String>) : RecyclerView.Adapter<UserAddressesAdapter.AddressHolder>() {
    var lastPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder {
        return AddressHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_user_address,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
     return 8
    }

    override fun onBindViewHolder(holder: AddressHolder, position: Int) {
        if (lastPosition == position) {
            holder.iv_check.setBackgroundResource(R.drawable.ic_action_done)
        } else {
            holder.iv_check.setBackgroundResource(R.drawable.ic_action_uncheck)
        }
      holder.main_layout.setOnClickListener {
          if (lastPosition >= 0)
              notifyItemChanged(lastPosition)
          lastPosition = holder.adapterPosition
          notifyItemChanged(lastPosition)
      }
    }

    class AddressHolder(view: View) : RecyclerView.ViewHolder(view) {
     val main_layout = view.layout_main
        val iv_check = view.ivCheck
    }
}