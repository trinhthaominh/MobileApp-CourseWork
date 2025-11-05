package com.example.bai16_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(private val ds: List<Int>) : RecyclerView.Adapter<RvAdapter.itemViewHolder>() {
    class itemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): itemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return itemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: itemViewHolder,
        position: Int
    ) {
        holder.itemView.apply {
            val imgAnh = findViewById<ImageView>(R.id.imgAnh)
            imgAnh.setImageResource(ds[position])

        }
    }
}