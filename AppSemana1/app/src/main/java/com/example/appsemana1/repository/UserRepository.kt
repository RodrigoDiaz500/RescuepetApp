package com.example.appsemana1.repository

import com.example.appsemana1.database.UserDao
import com.example.appsemana1.database.UserEntity

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun loginUser(email: String, password: String): UserEntity? {
        return userDao.getUserByCredentials(email, password)
    }

    suspend fun isEmailRegistered(email: String): Boolean {
        return userDao.getUserByEmail(email) != null
    }

    suspend fun findUserForRecovery(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }
}