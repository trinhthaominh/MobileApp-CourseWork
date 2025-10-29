package com.example.btcanhan_tuan4_bai2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btcanhan_tuan4_bai2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Enable edge-to-edge *before* setting content
        enableEdgeToEdge()

        // 2. Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Set content view using the root of the View Binding object
        setContentView(binding.root) // <-- ONLY ONE setContentView() call

        setUpDateTimePicker()

        setupInputFilters()

        setupTextWatchers()

        binding.btnCapNhat.setOnClickListener {
            if (isDataValid()) {
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                binding.txtName.text = binding.edtTen.text.toString()

            }
        }


    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPhoneValid(phone: String): Boolean {
        return phone.length == 10
    }

    private fun isIDValid(id: String): Boolean {
        return id.length == 12
    }

    private fun checkAllFieldsValid() {
        val hoTen = binding.edtTen.text?.isNotEmpty() == true
        val ngaySinh = binding.editTextDate.text?.isNotEmpty() == true
        val sdt = binding.edtSDT.text?.isNotEmpty() == true && isPhoneValid(binding.edtSDT.text.toString())
        val email = binding.edtEmail.text?.isNotEmpty() == true && isEmailValid(binding.edtEmail.text.toString())
        val cmt = binding.edtCMT.text?.isNotEmpty() == true && isIDValid(binding.edtCMT.text.toString())


        // Nút chỉ được bật (enabled) khi TẤT CẢ đều là TRUE
        binding.btnCapNhat.isEnabled = hoTen && ngaySinh && sdt && email && cmt
    }

    // Kiểm tra tổng quát tính hợp lệ của dữ liệu (bao gồm cả logic showError)
    private fun isDataValid(): Boolean {
        var isValid = true

        // Kiểm tra Email
        if (binding.edtEmail.text.isNullOrEmpty() || !isEmailValid(binding.edtEmail.text.toString())) {
            binding.edtEmail.error = "Email không hợp lệ"
            isValid = false
        } else {
            binding.edtEmail.error = null
        }

        // Kiểm tra SĐT
        if (binding.edtSDT.text.isNullOrEmpty() || !isPhoneValid(binding.edtSDT.text.toString())) {
            binding.edtSDT.error = "SĐT không hợp lệ (10 số)"
            isValid = false
        } else {
            binding.edtSDT.error = null
        }

        if (binding.edtCMT.text.isNullOrEmpty() || !isIDValid(binding.edtCMT.text.toString())) {
            binding.edtSDT.error = "CMT không hợp lệ (12 số)"
            isValid = false
        } else {
            binding.edtCMT.error = null
        }

        return isValid
    }


    private fun setupTextWatchers() {
        val fields = listOf(
            binding.edtTen,
            binding.edtSDT,
            binding.edtEmail,
            binding.edtCMT

        )

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {

                checkAllFieldsValid()


                if (binding.edtEmail.hasFocus() && s == binding.edtEmail.editableText) {
                    if (!s.isNullOrEmpty() && !isEmailValid(s.toString())) {
                        binding.edtEmail.error = "Email không hợp lệ"
                    } else {
                        binding.edtEmail.error = null
                    }
                }


                if (binding.edtSDT.hasFocus() && s == binding.edtSDT.editableText) {
                    if (!s.isNullOrEmpty() && !isPhoneValid(s.toString())) {
                        // Chỉ hiển thị lỗi nếu SĐT đã dài tối đa hoặc người dùng kết thúc nhập
                        binding.edtSDT.error = "SĐT không hợp lệ (10 số)"
                    } else {
                        binding.edtSDT.error = null
                    }
                }

                if (binding.edtCMT.hasFocus() && s == binding.edtCMT.editableText) {
                    if (!s.isNullOrEmpty() && !isIDValid(s.toString())) {
                        // Chỉ hiển thị lỗi nếu SĐT đã dài tối đa hoặc người dùng kết thúc nhập
                        binding.edtCMT.error = "CMT không hợp lệ (12 số)"
                    } else {
                        binding.edtCMT.error = null
                    }
                }
            }
        }

        fields.forEach { it.addTextChangedListener(watcher) }
    }
    private fun setupInputFilters() {
        binding.edtSDT.filters = arrayOf(InputFilter.LengthFilter(10))
        binding.edtCMT.filters = arrayOf(InputFilter.LengthFilter(12))
    }

    private fun setUpDateTimePicker() {
        binding.editTextDate.isFocusable = false
        binding.editTextDate.isCursorVisible = false

        binding.editTextDate.setOnClickListener {
            showDatePicker() // Gọi hàm hiển thị dialog
        }

    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        // Khởi tạo ngày/tháng/năm mặc định
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, // Context (Activity)
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.editTextDate.setText(dateFormat.format(calendar.time))
                //checkAllFieldsValid()
            },
            year,
            month,
            day
        )
        datePickerDialog.show()

    }
}
