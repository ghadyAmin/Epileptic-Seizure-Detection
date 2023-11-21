package com.example.epilepticseizuredetection.domain.bluetooth



typealias BluetoothDeviceDomain = BluetoothDevice
data class BluetoothDevice(
    val name: String?,
    val address: String
)