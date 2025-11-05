package com.example.btcanhan_tuan4_bai4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(
    private val folders: List<Folder>,
    private val listener: rvFolderInterface
) : RecyclerView.Adapter<RvAdapter.FolderViewHolder>() {

    inner class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_folder, parent, false)
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val folder = folders[position]
        holder.txtName.text = folder.name
        holder.txtDesc.text = folder.desc

        // ✅ Xử lý click
        holder.itemView.setOnClickListener {
            listener.onClickFolder(position)
        }
    }

    override fun getItemCount(): Int = folders.size
}
