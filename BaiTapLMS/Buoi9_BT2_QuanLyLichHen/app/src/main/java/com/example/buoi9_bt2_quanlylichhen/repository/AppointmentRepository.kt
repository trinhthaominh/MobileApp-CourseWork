package com.example.buoi9_bt2_quanlylichhen.repository

import com.example.buoi9_bt2_quanlylichhen.model.AppointmentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AppointmentRepository {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Appointments")

    suspend fun saveAppointment(appointment: AppointmentModel): Result<Unit> {
        return try {
            database.child(appointment.id ?: "").setValue(appointment).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteAppointment(appointmentId: String): Result<Unit> {
        return try {
            database.child(appointmentId).removeValue().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getAllAppointments(): Flow<List<AppointmentModel>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val appointments = mutableListOf<AppointmentModel>()
                for (data in snapshot.children) {
                    data.getValue(AppointmentModel::class.java)?.let {
                        appointments.add(it)
                    }
                }
                trySend(appointments)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        database.addValueEventListener(listener)

        awaitClose {
            database.removeEventListener(listener)
        }
    }

    fun generateNewId(): String {
        return database.push().key ?: ""
    }
}