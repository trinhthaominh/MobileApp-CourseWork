package com.example.bai17fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai17fragment.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sub1 = Fragment1()
        val sub2 = Fragment2()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.Fr1, sub1)
            commit()
        }

        binding.btnF1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.Fr1, sub1)
                commit()
            }
        }

        binding.btnF2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.Fr2, sub2)
                commit()
            }
        }


        }
}