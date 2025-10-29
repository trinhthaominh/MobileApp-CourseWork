package com.example.bai14_1intent

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai14_1intent.databinding.ActivityMainBinding
import com.example.bai14_1intent.databinding.ActivityManhinh2Binding

@SuppressLint("StaticFieldLeak")
private lateinit var binding2: ActivityManhinh2Binding

class manhinh2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Enable edge-to-edge *before* setting content
        enableEdgeToEdge()

        // 2. Initialize View Binding
        binding2 = ActivityManhinh2Binding.inflate(layoutInflater)

        // 3. Set content view using the root of the View Binding object
        setContentView(binding2.root) // <-- ONLY ONE setContentView() call
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // get dũ liệu
        val i = intent
        val bienString = i.getStringExtra("bienString")
        val bienInt = i.getIntExtra("bienInt",0)
        val bienBoolean = i.getBooleanExtra("bienBoolean",false)
        val bienDouble = i.getDoubleExtra("bienDouble",0.0)

        binding2.edtGetIntent.setText(bienString + "\n" + bienInt + "\n" + bienBoolean + "\n" + bienDouble)



        // gọi lệnh quay lại màn hinnh main
        binding2.btnQuayLai.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)}


    }
}