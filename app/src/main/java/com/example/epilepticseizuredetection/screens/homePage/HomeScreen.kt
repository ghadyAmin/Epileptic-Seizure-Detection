package com.example.epilepticseizuredetection.screens.homePage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.rememberAsyncImagePainter
import com.example.epilepticseizuredetection.R
import com.example.epilepticseizuredetection.screens.Navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
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
       val imageUri = rememberSaveable{mutableStateOf("")}
       val painter = rememberAsyncImagePainter(model =

           if (imageUri.value.isEmpty()){
              R.drawable.user
           }
           else{
               imageUri.value
           }
       )
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?. let { imageUri.value = it. toString() }

        }

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(16.dp)
                    .clickable { launcher.launch("image/*")
                    }
                ,
                contentScale = ContentScale.Crop
            )


    }

}
    }
