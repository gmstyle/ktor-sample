package com.example.repositories

import com.example.database.DatabaseConnector.database
import com.example.tables.AccountEntity
import com.example.tables.accounts
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.entity.find

class AccountRepository {

    fun getAccountByUsernameAndPassword(username: String, password: String): AccountEntity? {
        return database.accounts.find {
            it.username eq username and (it.password eq password)
        }
    }
}
