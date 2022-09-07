package com.example.models

import com.example.tables.AccountEntity
import kotlinx.serialization.Serializable

@Serializable
data class AccountModel(
    val id: Int,
    val username: String,
    val password: String
){
    companion object: BaseModel<AccountEntity, AccountModel>{
        override fun entityToObject(entity: AccountEntity): AccountModel {
            return AccountModel(
                id = entity.id,
                username = entity.username,
                password = entity.password
            )
        }

    }
}

