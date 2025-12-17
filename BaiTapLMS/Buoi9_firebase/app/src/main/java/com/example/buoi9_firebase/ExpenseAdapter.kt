package com.example.buoi9_firebase

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ExpenseAdapter(
    private val list: List<ExpenseModel>
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCategory: ImageView = itemView.findViewById(R.id.imgCategory)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvSub: TextView = itemView.findViewById(R.id.tvSub)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = list[position]

        holder.tvTitle.text = expense.title
        holder.tvSub.text = "${expense.amount} - ${expense.date}"

        Glide.with(holder.itemView.context)
            .load(expense.category?.imgUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.imgCategory)
    }

    override fun getItemCount(): Int = list.size
}
