package com.example.buoi9_bt2_quanlylichhen.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buoi9_bt2_quanlylichhen.R
import com.example.buoi9_bt2_quanlylichhen.databinding.ActivityMainBinding
import com.example.buoi9_bt2_quanlylichhen.databinding.DialogAppointmentBinding
import com.example.buoi9_bt2_quanlylichhen.model.AppointmentModel
import com.example.buoi9_bt2_quanlylichhen.ui.adapter.AppointmentAdapter
import com.example.buoi9_bt2_quanlylichhen.ui.viewmodel.AppointmentViewModel
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AppointmentViewModel
    private lateinit var adapter: AppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[AppointmentViewModel::class.java]

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        adapter = AppointmentAdapter(
            onEdit = { appointment -> showAppointmentDialog(appointment) },
            onDelete = { appointment -> showDeleteConfirmation(appointment) }
        )

        binding.rvAppointments.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupObservers() {
        // Observe appointments list
        viewModel.appointments.observe(this) { appointments ->
            adapter.submitList(appointments)

            // Show/hide empty state
            if (appointments.isEmpty()) {
                binding.tvEmptyState.visibility = View.VISIBLE
                binding.rvAppointments.visibility = View.GONE
            } else {
                binding.tvEmptyState.visibility = View.GONE
                binding.rvAppointments.visibility = View.VISIBLE
            }
        }

        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe error messages
        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.clearMessages()
            }
        }

        // Observe success messages
        viewModel.successMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.clearMessages()
            }
        }
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            showAppointmentDialog()
        }
    }

    private fun showAppointmentDialog(appointment: AppointmentModel? = null) {
        val dialogBinding = DialogAppointmentBinding.inflate(layoutInflater)

        // Pre-fill data if editing
        appointment?.let {
            dialogBinding.etPersonName.setText(it.personName)
            dialogBinding.etDateTime.setText(it.dateTime)
            dialogBinding.etImgUrl.setText(it.imgUrl)
            dialogBinding.etNote.setText(it.note)
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle(if (appointment == null) "Thêm Lịch Hẹn" else "Sửa Lịch Hẹn")
            .setView(dialogBinding.root)
            .setPositiveButton(if (appointment == null) "Thêm" else "Cập nhật") { _, _ ->
                val personName = dialogBinding.etPersonName.text.toString().trim()
                val dateTime = dialogBinding.etDateTime.text.toString().trim()
                val imgUrl = dialogBinding.etImgUrl.text.toString().trim()
                val note = dialogBinding.etNote.text.toString().trim()

                if (appointment == null) {
                    // Add new appointment
                    viewModel.addAppointment(personName, dateTime, imgUrl, note)
                } else {
                    // Update existing appointment
                    viewModel.updateAppointment(
                        appointment.id ?: "",
                        personName,
                        dateTime,
                        imgUrl,
                        note
                    )
                }
            }
            .setNegativeButton("Hủy", null)
            .create()

        dialog.show()
    }

    private fun showDeleteConfirmation(appointment: AppointmentModel) {
        AlertDialog.Builder(this)
            .setTitle("Xác nhận xóa")
            .setMessage("Bạn có chắc muốn xóa lịch hẹn với ${appointment.personName}?")
            .setPositiveButton("Xóa") { _, _ ->
                viewModel.deleteAppointment(appointment)
            }
            .setNegativeButton("Hủy", null)
            .show()
    }
}


