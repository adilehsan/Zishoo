package com.ideologer.zamishoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ideologer.zamishoapp.R

class PaymentHistoryAdapter(
    val context: Context,
    val itemList: ArrayList<String>
) : RecyclerView.Adapter<PaymentHistoryAdapter.PaymentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        return PaymentHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_payment_history,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return 15
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {

    }

    class PaymentHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

}