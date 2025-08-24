package com.example.appsemana1
import androidx.compose.runtime.mutableStateListOf


data class User(
    val username: String,
    val telefono: String,
    val correo: String,
    val ciudad: String,
    val contrasena: String
)


val userDatabase = mutableStateListOf(
    User("usuario1", "12345678", "user1@mail.com", "Santiago", "pass1"),
    User("usuario2", "87654321", "user2@mail.com", "Valparaíso", "pass2"),
    User("usuario3", "11223344", "user3@mail.com", "Concepción", "pass3"),
    User("usuario4", "44332211", "user4@mail.com", "Temuco", "pass4"),
    User("usuario5", "99887766", "user5@mail.com", "Antofagasta", "pass5"),
)