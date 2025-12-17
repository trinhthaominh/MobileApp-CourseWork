package com.example.buoi9_bt2_quanlylichhen.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AppointmentModel(
    val id: String? = null,
    val personName: String? = null,
    val dateTime: String? = null,
    val imgUrl: String? = null,
    val note: String? = null
)