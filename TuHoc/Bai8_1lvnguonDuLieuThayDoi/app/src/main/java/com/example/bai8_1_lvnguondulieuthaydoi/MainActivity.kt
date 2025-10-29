package com.example.bai8_1_lvnguondulieuthaydoi

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai8_1_lvnguondulieuthaydoi.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
//    Khai báo list rỗng
    var ds : MutableList<String> = mutableListOf("0901234567", "0901234568")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Enable edge-to-edge *before* setting content
        enableEdgeToEdge()

        // 2. Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Set content view using the root of the View Binding object
        setContentView(binding.root) // <-- ONLY ONE setContentView() call

        // 4. Set the window insets listener on the root view (binding.root) or the main container (@+id/main)
        // Since you used findViewById(R.id.main), we'll assume the root in your XML has android:id="@+id/main"
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Hiển thị ds ban đầu
        binding.lvSDT.adapter = ArrayAdapter(this, R.layout.simple_list_item_1, ds)

        addEvents()
    }

    private fun addEvents() {
        binding.btnLuu.setOnClickListener {
        xuLyNutLuu()}
    }

    private fun xuLyNutLuu() {
        var sdt = binding.edtSDT.text.toString()
        ds.add(sdt)
        binding.lvSDT.adapter = ArrayAdapter(this, R.layout.simple_list_item_1, ds)
        binding.edtSDT.setText("")
        }
}

