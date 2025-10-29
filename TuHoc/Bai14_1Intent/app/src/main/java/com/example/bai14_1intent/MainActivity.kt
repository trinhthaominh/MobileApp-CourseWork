package com.example.bai14_1intent

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai14_1intent.databinding.ActivityMainBinding
import androidx.core.net.toUri

@SuppressLint("StaticFieldLeak")
lateinit var binding: ActivityMainBinding
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

        // gọi màn hình 2
        binding.btnGo.setOnClickListener {
            val intent = Intent(this, manhinh2::class.java)

            // Truyền dữ liệu trực tiếp bằng Intent

            intent.putExtra("bienString","Chào mừng đến với tuhoc.cc")
            intent.putExtra("bienInt",2023)
            intent.putExtra("bienBoolean",true)
            intent.putExtra("bienDouble",3.14)

            startActivity(intent)
        }
        // gọi url
        binding.btnGo2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, "https://tuhoc.cc".toUri() )
            startActivity(intent)
        }


    }
}