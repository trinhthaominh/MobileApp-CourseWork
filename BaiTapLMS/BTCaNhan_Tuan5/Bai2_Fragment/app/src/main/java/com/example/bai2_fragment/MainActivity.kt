package com.example.bai2_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai2_fragment.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val fragment1 = Fragment1()
    val fragment2 = Fragment2()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var listFragment = listOf(fragment1, fragment2)

        binding.spinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.fragArr,
            android.R.layout.simple_spinner_item
        )
        initMainActivity()
        addEvent()

    }

    private fun initMainActivity() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment1)
            commit()
        }
    }

    private fun addEvent() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Fragment1()).commit()
                    1 -> supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Fragment2()).commit()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}