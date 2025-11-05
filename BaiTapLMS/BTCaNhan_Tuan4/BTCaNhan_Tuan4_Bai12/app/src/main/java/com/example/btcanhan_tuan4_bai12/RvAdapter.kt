package com.example.btcanhan_tuan4_bai12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(var lst:List<BookData>, val onBookClick : rvBookInterface) : RecyclerView.Adapter<RvAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return lst.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return BookViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int
    ) {
        holder.itemView.apply {
            val imgBook = findViewById<ImageView>(R.id.imgBook)
            imgBook.setImageResource(lst[position].img)
            val txtBookName = findViewById<TextView>(R.id.txtBookName)
            txtBookName.text = lst[position].name
            val txtDesc = findViewById<TextView>(R.id.txtDesc)
            txtDesc.text = lst[position].desc
            holder.itemView.setOnClickListener {
                onBookClick.onItemClick(position)
            }


        }
    }
}



