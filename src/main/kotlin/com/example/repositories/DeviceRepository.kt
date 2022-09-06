package com.example.repositories

import com.example.database.DatabaseConnector.database
import com.example.models.DeviceModel
import com.example.tables.DeviceEntity
import com.example.tables.devices
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.toList

class DeviceRepository {

    fun getAllDevices(): List<DeviceEntity> {
        return database.devices.toList()
    }

    fun getDevicesByUserId(userId: Int): List<DeviceEntity> {

        return database.devices.filter {
            it.fk_user_id eq userId
        }.toList()
    }

    fun getDeviceById(deviceId: Int): DeviceEntity? {

        println(deviceId)
        return database.devices.find {
            it.id eq deviceId
        }
    }

    fun insertDevice(device: DeviceModel, userIdProprietario: Int?): DeviceEntity {
        val addedDevice = DeviceEntity{
            nome = device.nome.toString()
            fkUserId = userIdProprietario
        }
        database.devices.add(addedDevice)
        return addedDevice
    }


}
