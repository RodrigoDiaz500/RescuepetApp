package com.example.appsemana1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    /** Inserta un nuevo usuario */
    @Insert
    suspend fun insertUser(user: UserEntity)

    /** Busca un usuario por credenciales para el Login */
    @Query("SELECT * FROM user_table WHERE correo = :email AND contrasena = :password LIMIT 1")
    suspend fun getUserByCredentials(email: String, password: String): UserEntity?

    /** Busca un usuario por correo para verificar si ya existe (Registro/Recuperación) */
    @Query("SELECT * FROM user_table WHERE correo = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?
}