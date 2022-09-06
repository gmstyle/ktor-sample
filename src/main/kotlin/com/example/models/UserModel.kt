package com.example.models

import com.example.tables.UserEntity
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int?,
    val nome: String?,
    val cognome: String?
){

    companion object: BaseModel<UserEntity, UserModel>{
        override fun entityToObject(entity: UserEntity): UserModel {
            return UserModel(
                id = entity.id,
                nome = entity.nome,
                cognome = entity.cognome
            )
        }

    }

}



