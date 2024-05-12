package com.example.todotasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todotasks.presentation.Screens.auth.AuthViewModel
import com.example.todotasks.presentation.navigation.destination.destination
import com.example.todotasks.presentation.navigation.nav.ButtonNav
import com.example.todotasks.presentation.navigation.nav.EventAppNavigation


import com.example.todotasks.ui.theme.TodoTasksTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseApp.
        enableEdgeToEdge()
        setContent {
            TodoTasksTheme {

                val authViewModel = hiltViewModel<AuthViewModel>()

                val navController =rememberNavController()
                var ahowButtomBar by rememberSaveable {
                    mutableStateOf(false)
                }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                ahowButtomBar =when(navBackStackEntry?.destination?.route){
                    destination.MainScreens.HomeRoute.title ->true
                    destination.MainScreens.TaskRoute.title ->true
                    destination.MainScreens.GraphicRoute.title ->true
                    destination.MainScreens.ProfileRoute.title ->true
                    else ->false
                }

                ButtonNav(navController = navController, showBottonBar =ahowButtomBar ) {

                EventAppNavigation(authViewModel,navController)
                }
            }
        }
    }
}

