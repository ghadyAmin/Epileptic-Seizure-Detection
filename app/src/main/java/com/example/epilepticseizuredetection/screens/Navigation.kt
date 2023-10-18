package com.example.epilepticseizuredetection.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.epilepticseizuredetection.presentation.viewModels.LoginViewModel
import com.example.epilepticseizuredetection.presentation.viewModels.RegistrationViewModel
import com.example.epilepticseizuredetection.screens.analytics.DataScreen
import com.example.epilepticseizuredetection.screens.homePage.HomeScreen
import com.example.epilepticseizuredetection.screens.login.LoginPage
import com.example.epilepticseizuredetection.screens.registeration.RegistrationScreen

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(navController, LoginViewModel())


        }
        composable("registration") {

            RegistrationScreen(navController, RegistrationViewModel())

    }

        composable("home_page") {

            HomeScreen(navController)


        }

        composable("data_screen") {

            DataScreen(navController = navController)


        }

}


}