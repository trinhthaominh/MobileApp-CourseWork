package com.example.bai1_customspinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai1_customspinner.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
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

        setUpCarSpinner()
    }

    private fun setUpCarSpinner() {
        var list = mutableListOf<Car>()
        list.add(Car(R.drawable.bmw, "BMW"))
        list.add(Car(R.drawable.cadillac, "Cadilac"))
        list.add(Car(R.drawable.chevy, "Chevy"))
        list.add(Car(R.drawable.ferrari, "Ferrari"))
        list.add(Car(R.drawable.honda, "Honda"))
        list.add(Car(R.drawable.mercedes, "Mercedes"))
        list.add(Car(R.drawable.porsche, "Porsche"))
        list.add(Car(R.drawable.toyota, "Toyota"))
        val carSpinner = CarSpinner(this, list)
        binding.spinner.adapter = carSpinner
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
           override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               Toast.makeText(this@MainActivity, "Bạn vừa chọn " + list[position].name, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                        }


        }
} }