package com.example.btcanhan_tuan4_bai4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btcanhan_tuan4_bai4.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RvAdapter
    private val folderList = mutableListOf<Folder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo adapter
        adapter = RvAdapter(folderList, object : rvFolderInterface {
            override fun onClickFolder(pos: Int) {
                val folder = folderList[pos]


                // Hiển thị log hoặc Toast để test click có chạy không
                Toast.makeText(
                    this@MainActivity,
                    "Click: ${folder.name}",
                    Toast.LENGTH_SHORT
                ).show()


                val intent = Intent(this@MainActivity, EditFolder::class.java)
                intent.putExtra("name", folder.name)
                intent.putExtra("desc", folder.desc)
                startActivity(intent)
            }
        })

        binding.rvFolder.adapter = adapter
        binding.rvFolder.layoutManager = LinearLayoutManager(this)

        // Đăng ký launcher để nhận kết quả từ AddFolder
        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val name = data?.getStringExtra("name")
                val desc = data?.getStringExtra("desc")

                if (!name.isNullOrEmpty() && !desc.isNullOrEmpty()) {
                    val folder = Folder(name, desc)
                    folderList.add(folder)
                    adapter.notifyItemInserted(folderList.size - 1)
                }
            }
        }



        binding.txtAdd.setOnClickListener {
            val intent = Intent(this, AddFolder::class.java)
            launcher.launch(intent)
        }


    }
}


