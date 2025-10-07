package com.example.appsemana1.recovery
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appsemana1.viewmodel.AuthState
import com.example.appsemana1.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarScreen(navController: NavController, authViewModel: AuthViewModel) {
    var correo by remember { mutableStateOf("") }
    var mostrarDialogoExito by remember { mutableStateOf(false) }


    LaunchedEffect(authViewModel.recoveryState) {
        when (authViewModel.recoveryState) {
            is AuthState.RegistrationSuccess -> {
                mostrarDialogoExito = true
                authViewModel.resetRecoveryState()
            }
            is AuthState.Error -> {

            }
            else -> { /* Idle, Loading */ }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFDD835))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Recuperar contraseña", fontSize = 28.sp, modifier = Modifier.padding(top = 24.dp, bottom = 24.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it; authViewModel.resetRecoveryState() },
                label = { Text("Correo Electrónico *") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFFFDD835))
            )

            // Mostrar error del ViewModel
            if (authViewModel.recoveryState is AuthState.Error) {
                Text(text = (authViewModel.recoveryState as AuthState.Error).message, fontSize = 18.sp, color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }


            Button(
                onClick = {
                    if (correo.isNotBlank()) {
                        authViewModel.recoverPassword(correo)
                    }
                },
                enabled = authViewModel.recoveryState != AuthState.Loading,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDD835))
            ) {
                if (authViewModel.recoveryState == AuthState.Loading) {
                    CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(24.dp))
                } else {
                    Text("Recuperar Contraseña", fontSize = 18.sp, color = Color.Black)
                }
            }
        }
    }

    // confirmación
    if (mostrarDialogoExito) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoExito = false },
            title = { Text("Confirmación",fontSize = 20.sp) },
            text = { Text("Si el correo está registrado, se enviará un enlace de restablecimiento (simulado).",fontSize = 18.sp) },
            confirmButton = {
                TextButton(onClick = { mostrarDialogoExito = false; navController.popBackStack() }) { Text("Aceptar") }
            }
        )
    }
}