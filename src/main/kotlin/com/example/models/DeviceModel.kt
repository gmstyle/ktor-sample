package com.example.models

import com.example.tables.DeviceEntity
import kotlinx.serialization.Serializable

@Serializable
data class DeviceModel(
    val id: Int?,
    var nome: String?,
    var user: UserModel?
){

    companion object : BaseModel<DeviceEntity, DeviceModel>{
        override fun entityToObject(entity: DeviceEntity): DeviceModel {
            return DeviceModel(
                id = entity.id,
                nome = entity.nome,
                user = UserModel.entityToObject(entity.user)
            )
        }

    }


}
