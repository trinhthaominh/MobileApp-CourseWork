package com.example.buoi9_firebase

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buoi9_firebase.databinding.ActivityMainBinding
import com.example.buoi9_firebase.databinding.DialogAddExpenseBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val expenses = mutableListOf<ExpenseModel>()
    private lateinit var adapter: ExpenseAdapter
    private lateinit var dbRef: DatabaseReference
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

        initeFireBase()
        binding.btnInsert.setOnClickListener {
            callDialogExpense()
        }
        binding.btnLoad.setOnClickListener {
            loadFirebase()
        }

        adapter = ExpenseAdapter(expenses)
        binding.rvExpenses.layoutManager = LinearLayoutManager(this)
        binding.rvExpenses.adapter = adapter

    }

    private fun callDialogExpense() {
        val dialogBinding = DialogAddExpenseBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Expense")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val idItem = dbRef.push().key
                val title = dialogBinding.etTitle.text.toString()
                val amount = dialogBinding.etAmount.text.toString().toDoubleOrNull() ?: 0.0
                val categoryName = dialogBinding.etCategory.text.toString()
                val categoryImg = dialogBinding.edtUrl.text.toString()
                val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())

                val category = CategoryModel(idItem, categoryName, categoryImg)

                val expense = ExpenseModel(
                    id = idItem,
                    title = title,
                    amount = amount,
                    category = category,
                    date = date.toString()
                )

                //viewModel.insertExpense(expense)
                saveExpenseToFirebase(expense)
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun initeFireBase() {
        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")
    }

private fun saveExpenseToFirebase(expense: ExpenseModel) {
    dbRef.child(expense.id ?: "").setValue(expense)
        .addOnSuccessListener {
            Toast.makeText(this, "successfully", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }
}
    private fun loadFirebase() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                expenses.clear()
                for (data in snapshot.children) {
                    data.getValue(ExpenseModel::class.java)?.let {
                        expenses.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}




