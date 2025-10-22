package com.example.bai5_1xulysukien

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtNhapA = findViewById<EditText>(R.id.edtNhapA)
        val edtNhapB = findViewById<EditText>(R.id.edtNhapB)
        val edtKq = findViewById<EditText>(R.id.edtKq)
        val btnCong = findViewById<Button>(R.id.btnCong)
        val btnReset = findViewById<Button>(R.id.btnReset)


//        Set text cho edtNhapA
//        edtNhapA.setText("o la la")

        btnCong.setOnClickListener {
            val A = edtNhapA.text.toString().toInt()
            val B = edtNhapB.text.toString().toInt()
            val kq = A + B

            edtKq.setText(kq.toString())

        }

        btnReset.setOnClickListener {
            edtKq.setText("")
            edtNhapA.setText("")
            edtNhapB.setText("")
            Toast.makeText(this, "Em đã xóa hết rồi nhé", Toast.LENGTH_SHORT).show()
        }
    }
}