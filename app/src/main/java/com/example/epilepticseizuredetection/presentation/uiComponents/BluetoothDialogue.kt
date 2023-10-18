package com.example.epilepticseizuredetection.presentation.uiComponents

import android.bluetooth.BluetoothDevice
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun BluetoothDevicesDialogue(
    devices : List<BluetoothDevice>,
    onDeviceSelected : (BluetoothDevice) -> Unit,
    onDismiss: () -> Unit
)
{
//Dialog(onDismissRequest = { onDismiss() }) {
//
//  devices.forEach{ device ->
//
//      Text(text = device.name ?: "Unknown Device")
//
//
//
//
//  }




    
}





