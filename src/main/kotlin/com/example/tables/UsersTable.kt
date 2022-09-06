package com.example.tables

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object UsersTable: Table<UserEntity>(tableName = "users") {

    val id = int("id").primaryKey().bindTo {it.id }
    val nome = varchar("nome").bindTo { it.nome }
    val cognome = varchar("cognome").bindTo { it.cognome }
}

interface UserEntity: Entity<UserEntity>{
    companion object : Entity.Factory<UserEntity>()
    val id: Int
    var nome: String
    var cognome: String

}

val Database.users get() = this.sequenceOf(UsersTable)

