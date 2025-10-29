package com.example.bai8_2lvnangcao

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai8_2lvnangcao.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    lateinit var customAdapter: CustomAdapter


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Enable edge-to-edge *before* setting content
        enableEdgeToEdge()

        // 2. Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Set content view using the root of the View Binding object
        setContentView(binding.root) // <-- ONLY ONE setContentView() call

//        Khai báo ds item list
        var list = mutableListOf<OutData>()
        list.add(OutData(R.drawable.banghoa, "Gia tộc rồng 2022", "Đây là miêu tả"))
        list.add(OutData(R.drawable.hoanhon, "Gia tộc rồng 2022", "Đây là miêu tả"))
        list.add(OutData(R.drawable.thanlan, "Gia tộc rồng 2022", "Đây là miêu tả"))
        list.add(OutData(R.drawable.banghoa, "Gia tộc rồng 2022", "Đây là miêu tả"))

        customAdapter = CustomAdapter(this, list)
//        Khai báo biến link đến lvPhim
        binding.lvPhim.adapter = customAdapter


    }
}