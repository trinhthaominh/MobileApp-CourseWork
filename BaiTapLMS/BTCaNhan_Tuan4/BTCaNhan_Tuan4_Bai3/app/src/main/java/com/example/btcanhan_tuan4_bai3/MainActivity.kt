package com.example.btcanhan_tuan4_bai3

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btcanhan_tuan4_bai3.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var dogGridView : DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Enable edge-to-edge *before* setting content
        enableEdgeToEdge()

        // 2. Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Set content view using the root of the View Binding object
        setContentView(binding.root)

        var lst = mutableListOf<DogData>()
        lst.add(DogData(R.drawable.dog1, "Dog1"))
        lst.add(DogData(R.drawable.dog2, "Dog2"))
        lst.add(DogData(R.drawable.dog3, "Dog3"))
        lst.add(DogData(R.drawable.dog4, "Dog4"))
        lst.add(DogData(R.drawable.dog5, "Dog5"))
        lst.add(DogData(R.drawable.dog6, "Dog6"))
        lst.add(DogData(R.drawable.dog7, "Dog7"))
        lst.add(DogData(R.drawable.dog8, "Dog8"))
        lst.add(DogData(R.drawable.dog9, "Dog9"))

        val dogAdapter = DogAdapter(this, lst)

        binding.gvResults.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedDog = lst[position]
            val descriptionText = selectedDog.desc
            showDescriptionDialog(descriptionText)
        }

        binding.gvResults.adapter = dogAdapter

    }
}

private fun MainActivity.showDescriptionDialog(descriptionText: String) {
    val builder = AlertDialog.Builder(this)

    builder.setTitle("Chi tiết về bé chó")
    builder.setMessage(descriptionText)
    builder.setPositiveButton("Đóng") { dialog, which ->
        dialog.dismiss()
    }
    val dialog: AlertDialog = builder.create()
    dialog.show()
}
