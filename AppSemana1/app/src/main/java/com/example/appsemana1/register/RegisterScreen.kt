package com.example.appsemana1.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appsemana1.User
import com.example.appsemana1.userDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var aceptoCondiciones by remember { mutableStateOf(false) }


    var mostrarErrorCorreo by remember { mutableStateOf(false) }
    var mostrarErrorCamposVacios by remember { mutableStateOf(false) }
    var mostrarDialogoExito by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
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
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de Usuario",
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 50.dp)
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    mostrarErrorCamposVacios = false
                },
                label = { Text("Nombre *", fontSize = 20.sp) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = telefono,
                onValueChange = {
                    telefono = it
                    mostrarErrorCamposVacios = false
                },
                label = { Text("Teléfono *", fontSize = 20.sp) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    mostrarErrorCorreo = false
                    mostrarErrorCamposVacios = false
                },
                label = { Text("Correo Electrónico *", fontSize = 20.sp) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )


            OutlinedTextField(
                value = ciudad,
                onValueChange = {
                    ciudad = it
                    mostrarErrorCamposVacios = false
                },
                label = { Text("Ciudad *", fontSize = 20.sp) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = contrasena,
                onValueChange = {
                    contrasena = it
                    mostrarErrorCamposVacios = false
                },
                label = { Text("Contraseña *", fontSize = 20.sp) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            //  error para correo ya registradp
            if (mostrarErrorCorreo) {
                Text(
                    text = "El correo ya está registrado.",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }


            TextButton(
                onClick = { /* TODO:  condiciones de uso */ },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(text = "Ver condiciones de uso", fontSize = 18.sp, color = Color.Blue)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Checkbox(checked = aceptoCondiciones, onCheckedChange = { aceptoCondiciones = it })
                Text(text = "Acepto las condiciones de uso", fontSize = 18.sp)
            }

            // error para campos vacíos
            if (mostrarErrorCamposVacios) {
                Text(
                    text = "Todos los campos con * son obligatorios.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp) ,
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = {

                    if (nombre.isBlank() || telefono.isBlank() || correo.isBlank() || ciudad.isBlank() || contrasena.isBlank()) {
                        mostrarErrorCamposVacios = true
                    } else {
                        val usuarioExistente = userDatabase.find { it.correo == correo }

                        if (usuarioExistente != null) {
                            mostrarErrorCorreo = true
                        } else if (aceptoCondiciones) {
                            val newUser = User(nombre, telefono, correo, ciudad, contrasena)
                            userDatabase.add(newUser)
                            mostrarDialogoExito = true
                        }
                    }
                },
                enabled = aceptoCondiciones,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDD835))
            ) {
                Text("Registrar", fontSize = 20.sp, color = Color.Black)
            }
        }
    }

    // registro exitoso
    if (mostrarDialogoExito) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoExito = false },
            title = { Text("Registro Exitoso",fontSize = 20.sp) },
            text = { Text("El usuario ha sido registrado correctamente.",fontSize = 18.sp) },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoExito = false
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
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
fun RegistroScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        RegistroScreen(navController)
    }
}