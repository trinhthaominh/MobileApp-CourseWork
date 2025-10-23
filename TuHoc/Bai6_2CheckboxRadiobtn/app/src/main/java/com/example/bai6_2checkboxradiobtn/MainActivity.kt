package com.example.bai6_2checkboxradiobtn

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai6_2checkboxradiobtn.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addEvents()

    }

    private fun addEvents() {
        // Xử lý sự kiện khi click vào nút chọn sở thích
        binding.btnChonSoThich.setOnClickListener {
            xuLyChonSoThich()
        }

        binding.btnXacNhan.setOnClickListener {
            xuLyXacNhan()
        }
    }


}

private fun xuLyChonSoThich() {
    var s : String = ""
    if (binding.chkNgheNhac.isChecked) {
        s += binding.chkNgheNhac.text.toString()+"\n"
    }
    if (binding.chkXemPhim.isChecked) {
        s += binding.chkXemPhim.text.toString()+"\n"
    }
    if (binding.chkChoiTheThao.isChecked) {
        s += binding.chkChoiTheThao.text.toString()+"\n"
    }
    if (binding.chkShopping.isChecked) {
        s += binding.chkShopping.text.toString()+"\n"
    }
    if (binding.chkDuLich.isChecked) {
        s += binding.chkDuLich.text.toString()+"\n"
    }

    binding.edtSoThich.setText(s.trimEnd())

}
private fun MainActivity.xuLyXacNhan() {
    var s : String = ""
    if (binding.radNam.isChecked) {
        s = binding.radNam.text.toString()
    } else if (binding.radNu.isChecked) {
        s = binding.radNu.text.toString()
    }

    if (s == "") {
        Toast.makeText(this, "Bạn chưa chọn giới tính", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(this, "Bạn vừa chọn giới tính " + s, Toast.LENGTH_SHORT).show()
    }
}