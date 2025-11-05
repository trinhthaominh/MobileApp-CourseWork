package com.example.btcanhan_tuan4_bai4

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.btcanhan_tuan4_bai4.databinding.ActivityEditFolderBinding

class EditFolder : AppCompatActivity() {

    private lateinit var binding3: ActivityEditFolderBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding3 = ActivityEditFolderBinding.inflate(layoutInflater)
        setContentView(binding3.root)

        val name = intent.getStringExtra("name") ?: ""
        val desc = intent.getStringExtra("desc") ?: ""

        binding3.edtName.setText(name)
        binding3.edtDesc.setText(desc)

        binding3.txtCancel.setOnClickListener {
            finish()
        }

        binding3.txtSave.setOnClickListener {
            val txtFolder = binding3.edtName.text.toString()
            val txtDesc = binding3.edtDesc.text.toString()
            val resultIntent = Intent().apply {
                // Gửi dữ liệu dưới dạng Extras
                putExtra("name", txtFolder)
                putExtra("desc", txtDesc) }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
