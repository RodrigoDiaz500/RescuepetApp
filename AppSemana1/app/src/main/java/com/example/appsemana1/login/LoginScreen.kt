package com.example.appsemana1.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appsemana1.R
import com.example.appsemana1.userDatabase

@Composable
fun LoginScreen(navController: NavController) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }
    var mostrarPopupExito by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de RescuePet",
            modifier = Modifier
                .size(400.dp)
                .padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                mostrarError = false
            },
            label = { Text("Correo Electrónico", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFDD835),
                unfocusedBorderColor = Color.Gray
            )
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = {
                contrasena = it
                mostrarError = false
            },
            label = { Text("Contraseña", fontSize = 20.sp) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFDD835),
                unfocusedBorderColor = Color.Gray
            )
        )

        Button(
            onClick = {
                val usuarioEncontrado = userDatabase.find { it.correo == correo && it.contrasena == contrasena }
                if (usuarioEncontrado != null) {
                    mostrarError = false
                    mostrarPopupExito = true
                } else {
                    mostrarError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDD835))
        ) {
            Text("Iniciar Sesión", fontSize = 20.sp, color = Color.Black)
        }

        if (mostrarError) {
            Text(
                text = "Correo o contraseña incorrectos.",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        TextButton(onClick = { navController.navigate("recuperar") }) {
            Text("Olvidé mi contraseña", fontSize = 18.sp, color = Color(0xFF8B0000))
        }

        TextButton(onClick = { navController.navigate("registro") }) {
            Text("¿No tienes una cuenta? Regístrate aquí", fontSize = 18.sp, color = Color.Blue)
        }
    }

    // inicio de sesión exitoso
    if (mostrarPopupExito) {
        AlertDialog(
            onDismissRequest = { mostrarPopupExito = false },
            title = { Text("Inicio de Sesión Exitoso",fontSize = 20.sp) },
            text = { Text("¡Bienvenido! El home se encuentra en desarrollo.",fontSize = 18.sp) },
            confirmButton = {
                TextButton(onClick = { mostrarPopupExito = false }) {
                    Text("Aceptar")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        LoginScreen(navController = navController)
    }
}