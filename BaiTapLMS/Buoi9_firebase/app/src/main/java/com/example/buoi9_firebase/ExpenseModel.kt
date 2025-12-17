package com.example.buoi9_firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ExpenseModel (
    val id: String? = null,
    val title: String? = null,
    val amount: Double? = null,
    var category: CategoryModel? = null,
    val date: String? = null,
)



data class CategoryModel (
    val id: String? = null,
    val category: String? = null,
    val imgUrl : String? = null,
)