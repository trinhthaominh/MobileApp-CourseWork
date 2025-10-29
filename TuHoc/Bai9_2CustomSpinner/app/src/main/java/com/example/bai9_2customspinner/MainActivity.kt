package com.example.bai9_2customspinner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai9_2customspinner.databinding.ActivityMainBinding

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

        setUpCustomeSpinner()

    }

    private fun setUpCustomeSpinner() {
//        Khai báo lst món ăn
        var lst2 = mutableListOf<OutData>()
        lst2.add(OutData(R.drawable.cam, "Cam"))
        lst2.add(OutData(R.drawable.duahau, "Dưa hấu"))
        lst2.add(OutData(R.drawable.sauchung, "Sầu chung"))
        lst2.add(OutData(R.drawable.xoai, "Xoài lắc nam bộ"))
        lst2.add(OutData(R.drawable.tao, "Táo"))

        val customerSpinner = CustomSpinner(this, lst2)
        binding.spCustom.adapter = customerSpinner
        binding.spCustom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
             {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity, "Bạn chọn " + lst2[position].mieuta, Toast.LENGTH_SHORT).show()
            }

                 override fun onNothingSelected(parent: AdapterView<*>?) {

                 }

             }

    }
}