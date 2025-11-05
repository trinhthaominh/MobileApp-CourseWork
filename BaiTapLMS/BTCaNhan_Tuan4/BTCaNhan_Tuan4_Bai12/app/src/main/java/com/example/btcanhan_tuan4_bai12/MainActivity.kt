package com.example.btcanhan_tuan4_bai12

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.btcanhan_tuan4_bai12.databinding.ActivityMainBinding

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

        val bookLst = mutableListOf<BookData>()
        bookLst.add(BookData(R.drawable.harrypotter1, "Harry Potter 1", "Harry Potter là một series tiểu thuyết..."))
        bookLst.add(BookData(R.drawable.harrypotter2, "Harry Potter 2", "Harry Potter là một series tiểu thuyết..."))
        bookLst.add(BookData(R.drawable.harrypotter3, "Harry Potter 3", "Harry Potter là một series tiểu thuyết..."))
        bookLst.add(BookData(R.drawable.harrypotter4, "Harry Potter 4", "Harry Potter là một series tiểu thuyết..."))
        bookLst.add(BookData(R.drawable.harrypotter5, "Harry Potter 5", "Harry Potter là một series tiểu thuyết..."))
        bookLst.add(BookData(R.drawable.harrypotter6, "Harry Potter 6", "Harry Potter là một series tiểu thuyết..."))
        bookLst.add(BookData(R.drawable.harrypotter7, "Harry Potter 7", "Harry Potter là một series tiểu thuyết..."))
        bookLst.add(BookData(R.drawable.harrypotter8, "Harry Potter 8", "Harry Potter là một series tiểu thuyết..."))

        val adapter = RvAdapter(bookLst, object : rvBookInterface {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity,
                    "Ban chon ${bookLst[position].name}",
                    Toast.LENGTH_SHORT).show()
            }
        })

        binding.rvBook.adapter = adapter
        binding.rvBook.layoutManager = GridLayoutManager(this,
            3, GridLayoutManager.HORIZONTAL,false)

    }
}