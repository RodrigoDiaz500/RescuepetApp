package com.example.appsemana1.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val username: String,
    val telefono: String,
    val correo: String,
    val ciudad: String,
    val contrasena: String
)