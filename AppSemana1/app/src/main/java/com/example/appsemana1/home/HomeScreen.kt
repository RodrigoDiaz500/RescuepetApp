package com.example.appsemana1.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Mascota(
    val nombre: String,
    val raza: String,
    val edad: Int
)

val listaMascotas = listOf(
    Mascota("Fido", "Golden Retriever", 3),
    Mascota("Luna", "Gato ", 1),
    Mascota("Max", "Pastor Alemán", 5),
    Mascota("Coco", "Poodle", 2),
    Mascota("Pipo", "Labrador", 4)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, nombreUsuario: String) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "¡Hola, $nombreUsuario!", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                modifier = Modifier.padding(bottom = 16.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFDD835)
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listaMascotas) { mascota ->
                MascotaCard(mascota)
            }
        }
    }
}

@Composable
fun MascotaCard(mascota: Mascota) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = mascota.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Raza: ${mascota.raza}")
            Text(text = "Edad: ${mascota.edad} años")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        HomeScreen(navController = navController, nombreUsuario = "Usuario")
    }
}