package com.example.baitap_tuan03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baitap_tuan03.databinding.ActivityDangKyMhBinding
import com.example.baitap_tuan03.databinding.ActivityLoginBinding

class DangKyMH : AppCompatActivity() {

    lateinit var binding: ActivityDangKyMhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dang_ky_mh)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityDangKyMhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        binding.txtViewHello.text = "Xin chào $username \n Hãy đăng ký môn học"

    }
}