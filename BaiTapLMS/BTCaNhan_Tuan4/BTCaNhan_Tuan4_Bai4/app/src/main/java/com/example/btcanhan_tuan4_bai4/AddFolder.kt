package com.example.btcanhan_tuan4_bai4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btcanhan_tuan4_bai4.databinding.ActivityAddFolderBinding
import com.example.btcanhan_tuan4_bai4.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
lateinit var binding2 : ActivityAddFolderBinding
class AddFolder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_folder)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_folder)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding2 = ActivityAddFolderBinding.inflate(layoutInflater)

        // 3. Set content view using the root of the View Binding object
        setContentView(binding2.root)

        binding2.txtCancel.setOnClickListener {
            finish()
        }

        binding2.txtSave.setOnClickListener {
            val txtFolder = binding2.edtName.text.toString()
            val txtDesc = binding2.edtDesc.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            val resultIntent = Intent().apply {
                // Gửi dữ liệu dưới dạng Extras
                putExtra("name", txtFolder)
                putExtra("desc", txtDesc) }
            setResult(RESULT_OK, resultIntent)
            finish()

        }

    }
}