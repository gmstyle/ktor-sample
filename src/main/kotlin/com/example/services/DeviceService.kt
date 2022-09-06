package com.example.services

import com.example.models.DeviceModel

interface DeviceService {
    fun getAllDevices(): List<DeviceModel>
    fun getDevicesByUserId(userId: Int): List<DeviceModel>
    fun getDeviceById(deviceId: Int): DeviceModel?
    fun insertDevice(device: DeviceModel, userIdProprietario: Int?) : DeviceModel
}
