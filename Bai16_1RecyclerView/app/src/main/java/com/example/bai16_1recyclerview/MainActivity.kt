package com.example.bai16_1recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bai16_1recyclerview.databinding.ActivityMainBinding

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

        val ds = mutableListOf<OutData>()
        ds.add(OutData(R.drawable.banghoa,"Bang hoa", "Mo ta"))
        ds.add(OutData(R.drawable.hoanhon,"Hoan hon", "Mo ta"))
        ds.add(OutData(R.drawable.giatocrong,"Gia toc rong", "Mo ta"))
        ds.add(OutData(R.drawable.thanlan,"Than lan", "Mo ta"))
        ds.add(OutData(R.drawable.banghoa,"Bang hoa", "Mo ta"))
        ds.add(OutData(R.drawable.hoanhon,"Hoan hon", "Mo ta"))

        val adapter = RvAdapter(ds, object : rvInterface {
            override fun onClickPhim(pos: Int) {
                Toast.makeText(this@MainActivity,
                    "Ban chon phim ${ds[pos].tenPhim}",
                    Toast.LENGTH_SHORT).show()
            }
        })
        binding.rvPhim.adapter = adapter
//
//        binding.rvPhim.layoutManager = LinearLayoutManager(this,
//            LinearLayoutManager.HORIZONTAL, false)

        binding.rvPhim.layoutManager = GridLayoutManager(this,
            2, GridLayoutManager.HORIZONTAL,false)

    }

}