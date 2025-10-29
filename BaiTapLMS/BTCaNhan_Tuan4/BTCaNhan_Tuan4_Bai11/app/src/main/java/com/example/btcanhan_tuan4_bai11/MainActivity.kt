package com.example.btcanhan_tuan4_bai11

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btcanhan_tuan4_bai11.databinding.ActivityMainBinding

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

        var lstBook= mutableListOf<BookData>()

        lstBook.add(BookData(R.drawable.harrypotter1, "Harry Potter 1", "Đây là mô tả của Harry Potter 1"))
        lstBook.add(BookData(R.drawable.harrypotter2, "Harry Potter 2", "Đây là mô tả của Harry Potter 2"))
        lstBook.add(BookData(R.drawable.harrypotter3, "Harry Potter 3", "Đây là mô tả của Harry Potter 3"))
        lstBook.add(BookData(R.drawable.harrypotter4, "Harry Potter 4", "Đây là mô tả của Harry Potter 4"))
        lstBook.add(BookData(R.drawable.harrypotter5, "Harry Potter 5", "Đây là mô tả của Harry Potter 5"))
        lstBook.add(BookData(R.drawable.harrypotter6, "Harry Potter 6", "Đây là mô tả của Harry Potter 6"))

        // Thiết lập sự kiện click
        binding.lvBook.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedBook = lstBook[position]
            val descriptionText = selectedBook.desc
            showDescriptionDialog(descriptionText)
        }

        binding.lvBook.adapter = BookAdapter(this, lstBook)

    }
}

private fun MainActivity.showDescriptionDialog(descriptionText: String) {
    val builder = AlertDialog.Builder(this)

    builder.setTitle("Mô tả sách")
    builder.setMessage(descriptionText)
    builder.setPositiveButton("Đóng") { dialog, which ->
        dialog.dismiss()
    }
    val dialog: AlertDialog = builder.create()
    dialog.show()
}
