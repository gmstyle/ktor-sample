package com.example.tables

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AccountTable: Table<AccountEntity>(tableName = "accounts"){
    val id = int("id").primaryKey().bindTo { it.id }
    val username = varchar("username").bindTo { it.username }
    val password = varchar("password").bindTo { it.password }

}

interface AccountEntity: Entity<AccountEntity>{
    companion object: Entity.Factory<AccountEntity>()

    var id: Int
    var username: String
    var password: String
}

val Database.accounts get() = this.sequenceOf(AccountTable)
