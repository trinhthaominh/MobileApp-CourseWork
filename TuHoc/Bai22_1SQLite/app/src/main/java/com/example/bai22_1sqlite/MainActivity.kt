package com.example.bai22_1sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai22_1sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("Recycle")
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

        val helper = MyDbHelper(applicationContext)
        val db = helper.writableDatabase
        val rs = db.rawQuery("SELECT * FROM USERS",null)

        if (rs.moveToFirst()) {
            Toast.makeText(this, rs.getString(1), Toast.LENGTH_SHORT).show()
        }

        binding.btnAdd.setOnClickListener {
            var cv = ContentValues()

            val username = binding.edtUserName.text.toString()
            val pwd = binding.edtPwd.text.toString()

            cv.put("USERNAME", username)
            cv.put("PWD", pwd)
            db.insert("USERS", null, cv)
            Toast.makeText(applicationContext, "Add success", Toast.LENGTH_SHORT).show()
            binding.edtUserName.setText("")
            binding.edtPwd.setText("")
            binding.edtUserName.requestFocus()

        }

    }
}