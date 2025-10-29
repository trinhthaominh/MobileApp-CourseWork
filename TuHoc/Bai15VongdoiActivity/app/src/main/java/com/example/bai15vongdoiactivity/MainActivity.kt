package com.example.bai15vongdoiactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai15vongdoiactivity.databinding.ActivityMainBinding

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
        setContentView(binding.root)

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

        binding.btnGo.setOnClickListener {
            val intent = Intent(this, manhinh2::class.java)
            startActivity(intent)}

        binding.btnGo3.setOnClickListener {
            val intent = Intent(this, manhinh3::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }

     override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }


}