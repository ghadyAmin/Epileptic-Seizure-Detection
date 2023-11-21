package com.example.epilepticseizuredetection.screens.homePage

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.rememberAsyncImagePainter
import com.example.epilepticseizuredetection.R
import com.example.epilepticseizuredetection.controller.HeartRateDataSource
import com.example.epilepticseizuredetection.data.BluetoothManager
import com.example.epilepticseizuredetection.data.heartRateThreshold
import com.example.epilepticseizuredetection.domain.bluetooth.BluetoothViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,context: Context, heartRateSensor: HeartRateDataSource ){
   // val bluetoothManager = BluetoothManager(context)

    BluetoothManager(context)
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

                NavigationBarItem(
                    selected = false,
                    onClick = {  navController.navigate("account_screen"){
                        //navigation builder
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    } },
                    icon = { Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "account"
                    )
                    },  label = { Text(text = "account") }
                )

            }
        }





    ) {paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)
            ){

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

//            Image(
//                painter = painter,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(200.dp)
//                    .padding(16.dp)
//                    .clickable {
//                        launcher.launch("image/*")
//                    }
//                ,
//                contentScale = ContentScale.Crop
//
//            )

            var heartRate by remember { mutableStateOf(0) }



            val viewModel = hiltViewModel<BluetoothViewModel>()
            val state by viewModel.state.collectAsState()

            LaunchedEffect(heartRateSensor) {
                heartRateSensor.start(context = context)
                heartRateSensor.getHeartRate().collect {
                    heartRate = it
                    heartRateThreshold(heartRate, context)

                }
            }

         Column(modifier = Modifier
             .fillMaxSize()
             .padding(100.dp),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center) {
             Text(text = "Heart Rate: $heartRate",
                 modifier = Modifier.padding(16.dp)
                 ,fontSize = 24.sp)
         }





//DeviceScreen(
//    state = state,
//    onStartScan = viewModel::startScan,
//    onStopScan = viewModel::stopScan
//)



    }



    }




    }
