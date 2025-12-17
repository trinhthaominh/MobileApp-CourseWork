package com.example.buoi9_bt2_quanlylichhen.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buoi9_bt2_quanlylichhen.model.AppointmentModel
import com.example.buoi9_bt2_quanlylichhen.repository.AppointmentRepository
import kotlinx.coroutines.launch


class AppointmentViewModel : ViewModel() {

    private val repository = AppointmentRepository()

    private val _appointments = MutableLiveData<List<AppointmentModel>>()
    val appointments: LiveData<List<AppointmentModel>> = _appointments

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> = _successMessage

    init {
        loadAppointments()
    }
    private fun loadAppointments() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getAllAppointments().collect { appointmentsList ->
                    _appointments.value = appointmentsList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Lỗi tải dữ liệu: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun addAppointment(
        personName: String,
        dateTime: String,
        imgUrl: String,
        note: String
    ) {
        if (personName.isBlank() || dateTime.isBlank()) {
            _errorMessage.value = "Vui lòng nhập đầy đủ thông tin"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            val id = repository.generateNewId()
            val appointment = AppointmentModel(
                id = id,
                personName = personName,
                dateTime = dateTime,
                imgUrl = imgUrl,
                note = note
            )

            repository.saveAppointment(appointment).fold(
                onSuccess = {
                    _successMessage.value = "Thêm lịch hẹn thành công"
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _errorMessage.value = "Thêm thất bại: ${exception.message}"
                    _isLoading.value = false
                }
            )
        }
    }

    fun updateAppointment(
        id: String,
        personName: String,
        dateTime: String,
        imgUrl: String,
        note: String
    ) {
        if (personName.isBlank() || dateTime.isBlank()) {
            _errorMessage.value = "Vui lòng nhập đầy đủ thông tin"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            val appointment = AppointmentModel(
                id = id,
                personName = personName,
                dateTime = dateTime,
                imgUrl = imgUrl,
                note = note
            )

            repository.saveAppointment(appointment).fold(
                onSuccess = {
                    _successMessage.value = "Cập nhật thành công"
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _errorMessage.value = "Cập nhật thất bại: ${exception.message}"
                    _isLoading.value = false
                }
            )
        }
    }

    fun deleteAppointment(appointment: AppointmentModel) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.deleteAppointment(appointment.id ?: "").fold(
                onSuccess = {
                    _successMessage.value = "Xóa thành công"
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _errorMessage.value = "Xóa thất bại: ${exception.message}"
                    _isLoading.value = false
                }
            )
        }
    }

    // Clear messages after showing
    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}