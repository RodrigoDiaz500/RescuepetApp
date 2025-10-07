package com.example.appsemana1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appsemana1.database.UserEntity
import com.example.appsemana1.repository.UserRepository
import kotlinx.coroutines.launch

// Estados que manejarán las vistas
sealed interface AuthState {
    object Idle : AuthState
    object Loading : AuthState
    object LoginSuccess : AuthState
    object RegistrationSuccess : AuthState
    data class Error(val message: String) : AuthState
}

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var registrationState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    var loginState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    var recoveryState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    fun registerUser(user: UserEntity) {
        viewModelScope.launch {
            registrationState = AuthState.Loading

            val emailExists = repository.isEmailRegistered(user.correo)
            if (emailExists) {
                registrationState = AuthState.Error("El correo ya se encuentra registrado.")
                return@launch
            }

            try {
                repository.registerUser(user)
                registrationState = AuthState.RegistrationSuccess
            } catch (e: Exception) {
                registrationState = AuthState.Error("Error al guardar en la base de datos: ${e.message}")
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginState = AuthState.Loading

            val user = repository.loginUser(email, password)

            if (user != null) {
                loginState = AuthState.LoginSuccess
            } else {
                loginState = AuthState.Error("Credenciales incorrectas. Verifique correo y/o contraseña.")
            }
        }
    }

    fun recoverPassword(email: String) {
        viewModelScope.launch {
            recoveryState = AuthState.Loading

            val user = repository.findUserForRecovery(email)

            if (user != null) {
                recoveryState = AuthState.RegistrationSuccess
            } else {
                recoveryState = AuthState.Error("El correo no se encuentra registrado.")
            }
        }
    }

    fun resetRegistrationState() { registrationState = AuthState.Idle }
    fun resetLoginState() { loginState = AuthState.Idle }
    fun resetRecoveryState() { recoveryState = AuthState.Idle }
}

class AuthViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}