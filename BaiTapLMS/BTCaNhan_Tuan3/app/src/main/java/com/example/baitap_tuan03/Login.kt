package com.example.baitap_tuan03

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baitap_tuan03.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val btnLogin = findViewById<Button>(R.id.btnLogin)
//        var editText = findViewById<EditText>(R.id.editUname)
//        binding.btnLogin.setOnClickListener {this}
//            Toast.makeText(this,binding.editUname.text.toString(), Toast.LENGTH_LONG).show()
//        }
        addEvent()

    }
    private fun addEvent(){
        binding.btnLogin.setOnClickListener {
            buttonLogin()
        }

    }

    private fun buttonLogin(){
//        Toast.makeText(this,binding.editUname.text.toString(), Toast.LENGTH_LONG).show()
        val i = Intent(this, DangKyMH::class.java)
        i.putExtra("Uname", binding.editUname.text.toString())
        startActivity(i)

    }
}