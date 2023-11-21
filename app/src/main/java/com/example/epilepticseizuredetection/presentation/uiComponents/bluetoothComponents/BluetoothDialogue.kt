package com.example.epilepticseizuredetection.presentation.uiComponents.bluetoothComponents

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog


@SuppressLint("MissingPermission")
@Composable
fun BluetoothDevicesDialogue(
    devices : List<BluetoothDevice>,
    onDeviceSelected : (BluetoothDevice) -> Unit,
    onDismiss: () -> Unit
)
{
Dialog(onDismissRequest = { onDismiss() }) {

  devices.forEach{ device ->

      Text(text = device.name ?: "Unknown Device",
          modifier = Modifier.clickable {
              onDeviceSelected(device)
          }
      )
  }
    
    TextButton(onClick = { onDismiss() }) {
        Text(text = "cancel")
    }
    
    
}

  



}





