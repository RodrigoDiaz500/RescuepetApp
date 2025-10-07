package com.example.appsemana1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appsemana1.database.AppDatabase
import com.example.appsemana1.recovery.RecuperarScreen
import com.example.appsemana1.register.RegistroScreen
import com.example.appsemana1.repository.UserRepository
import com.example.appsemana1.ui.theme.AppSemana1Theme
import com.example.appsemana1.viewmodel.AuthViewModel
import com.example.appsemana1.viewmodel.AuthViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSemana1Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current


    val database = remember { AppDatabase.getDatabase(context) }

    val repository = remember { UserRepository(database.userDao()) }

    val factory = remember { AuthViewModelFactory(repository) }


    val authViewModel: AuthViewModel = viewModel(factory = factory)

    NavHost(navController = navController, startDestination = "login") {

        composable("recuperar") { RecuperarScreen(navController, authViewModel) }
    }
}
