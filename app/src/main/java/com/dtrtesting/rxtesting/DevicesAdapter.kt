package com.dtrtesting.rxtesting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.polidea.rxandroidble2.RxBleDevice

class DevicesAdapter(private val items: ArrayList<RxBleDevice>) : RecyclerView.Adapter<DevicesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val LargeText: TextView = itemView.findViewById(R.id.name)
        val SmallText: TextView = itemView.findViewById(R.id.mac)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.LargeText.text = items[position].name
        holder.SmallText.text = items[position].macAddress
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.device_item, parent, false))
    }

    override fun getItemCount(): Int = items.size

    fun setOnItemClick(onItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}