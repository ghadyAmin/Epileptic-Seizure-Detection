package com.example.epilepticseizuredetection.screens.analytics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.epilepticseizuredetection.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScreen(navController: NavController){

    Scaffold(
        bottomBar = {
            BottomAppBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {  navController.navigate("home_page"){
                        //navigation builder
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    } },
                    icon = { Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "home"
                    )
                    },  label = { Text(text = "Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {  navController.navigate("data_screen"){
                        //navigation builder
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    } },
                    icon = { Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "data"
                    )
                    },  label = { Text(text = "data") }
                )

            }
        }





    ) {paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){

        }

    }

}