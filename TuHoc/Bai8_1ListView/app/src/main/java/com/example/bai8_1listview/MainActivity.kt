package com.example.bai8_1listview

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai8_1listview.databinding.ActivityMainBinding
import androidx.core.graphics.toColorInt

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

        // 4. Set the window insets listener on the root view (binding.root) or the main container (@+id/main)
        // Since you used findViewById(R.id.main), we'll assume the root in your XML has android:id="@+id/main"
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mauXanh = "#99FFFF".toColorInt()

        binding.lvQuocGia.setBackgroundColor(mauXanh)

        addEvents()

    }

    private fun addEvents() {
        hienthilvQuocGia()
    }

    private fun hienthilvQuocGia() {
        var arrQuocGia = resources.getStringArray(R.array.arrQuocGia)

        binding.lvQuocGia.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, arrQuocGia)

        binding.lvQuocGia.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "Bạn chọn " + arrQuocGia[i], Toast.LENGTH_SHORT).show()

        }
    }
}