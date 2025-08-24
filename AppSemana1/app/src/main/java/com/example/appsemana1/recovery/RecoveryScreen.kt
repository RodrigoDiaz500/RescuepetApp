package com.example.appsemana1.recovery

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appsemana1.User
import com.example.appsemana1.userDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarScreen(navController: NavController) {
    var correo by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }
    var mostrarDialogo by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFDD835)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Recuperar contraseña",
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            )

           
            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    mostrarError = false
                },
                label = { Text("Correo Electrónico *") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFDD835),
                    unfocusedBorderColor = Color.Gray
                )
            )

            // usuario no  registrado
            if (mostrarError) {
                Text(
                    text = "Usuario no registrado",fontSize = 18.sp,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }


            Button(
                onClick = {
                    val usuarioEncontrado = userDatabase.find { it.correo == correo }
                    if (usuarioEncontrado != null) {
                        mostrarDialogo = true
                    } else {
                        mostrarError = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDD835))
            ) {
                Text("Recuperar Contraseña", fontSize = 18.sp, color = Color.Black)
            }
        }
    }

    // confirmación
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text("Confirmación",fontSize = 20.sp) },
            text = { Text("Se enviará un correo para restablecer su contraseña.",fontSize = 18.sp) },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                        navController.popBackStack()
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RecuperarScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        RecuperarScreen(navController = navController)
    }
}