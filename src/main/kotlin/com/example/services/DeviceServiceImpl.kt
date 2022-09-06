package com.example.services

import com.example.models.DeviceModel
import com.example.repositories.DeviceRepository
import org.koin.java.KoinJavaComponent.inject

class DeviceServiceImpl : DeviceService {

private val deviceRepository: DeviceRepository by inject(DeviceRepository::class.java)

    override fun getAllDevices(): List<DeviceModel> {
        return deviceRepository.getAllDevices().map {
            DeviceModel.entityToObject(it)
        }
    }

    override fun getDevicesByUserId(userId: Int): List<DeviceModel> {
        return deviceRepository.getDevicesByUserId(userId).map {
            DeviceModel.entityToObject(it)
        }
    }

    override fun getDeviceById(deviceId: Int): DeviceModel? {
        return deviceRepository.getDeviceById(deviceId)?.let {
            DeviceModel.entityToObject(it) }
    }

    override fun insertDevice(device: DeviceModel, userIdProprietario: Int?): DeviceModel {
        val addedDevice = deviceRepository.insertDevice(device, userIdProprietario)
        return DeviceModel.entityToObject(addedDevice)
    }

}
