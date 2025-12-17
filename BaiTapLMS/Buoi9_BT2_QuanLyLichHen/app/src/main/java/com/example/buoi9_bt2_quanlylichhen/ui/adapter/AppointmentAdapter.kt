package com.example.buoi9_bt2_quanlylichhen.ui.adapter




import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.buoi9_bt2_quanlylichhen.R
import com.example.buoi9_bt2_quanlylichhen.databinding.ItemAppointmentBinding
import com.example.buoi9_bt2_quanlylichhen.model.AppointmentModel


class AppointmentAdapter(
    private val onEdit: (AppointmentModel) -> Unit,
    private val onDelete: (AppointmentModel) -> Unit
) : ListAdapter<AppointmentModel, AppointmentAdapter.AppointmentViewHolder>(AppointmentDiffCallback()) {

    inner class AppointmentViewHolder(
        private val binding: ItemAppointmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(appointment: AppointmentModel) {
            binding.apply {
                tvPersonName.text = appointment.personName
                tvDateTime.text = appointment.dateTime
                tvNote.text = appointment.note ?: "Không có ghi chú"

                // Load image with Glide
                Glide.with(imgPerson.context)
                    .load(appointment.imgUrl)
                    .placeholder(binding.imgPerson.drawable)
                    .error(binding.imgPerson.drawable)
                    .circleCrop()
                    .into(imgPerson)

                // Click listeners
                btnEdit.setOnClickListener { onEdit(appointment) }
                btnDelete.setOnClickListener { onDelete(appointment) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = ItemAppointmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AppointmentDiffCallback : DiffUtil.ItemCallback<AppointmentModel>() {
    override fun areItemsTheSame(oldItem: AppointmentModel, newItem: AppointmentModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AppointmentModel, newItem: AppointmentModel): Boolean {
        return oldItem == newItem
    }
}