package com.example.bai16_1recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bai16_1recyclerview.databinding.ActivityMainBinding


class RvAdapter(var  ds:List<OutData>, val onPhimClick : rvInterface) : RecyclerView.Adapter<RvAdapter.PhimViewHolder>() {
    inner class PhimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhimViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item,parent,false)
        return PhimViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: PhimViewHolder,
        position: Int
    ) {
        holder.itemView.apply {
            val txtTenPhim = findViewById<TextView>(R.id.txtTenPhim)
            val txtMoTa = findViewById<TextView>(R.id.txtMoTa)
            val imgPhim = findViewById<ImageView>(R.id.imgPhim)

            txtTenPhim.text = ds[position].tenPhim
            txtMoTa.text = ds[position].moTa
            imgPhim.setImageResource(ds[position].img)

            holder.itemView.setOnClickListener {
                onPhimClick.onClickPhim(position)
            }

        }
    }
}
