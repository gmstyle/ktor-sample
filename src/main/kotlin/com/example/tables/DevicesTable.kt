package com.example.tables

import org.ktorm.database.Database
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf

object DevicesTable : Table<DeviceEntity>("devices") {
    val id = int("id").primaryKey().bindTo { it.id }
    var nome = varchar("nome").bindTo { it.nome }
    var fk_user_id = int("fk_user_id").bindTo { it.fkUserId }
        .references(UsersTable){ it.user }
}

interface DeviceEntity: Entity<DeviceEntity> {
    companion object : Entity.Factory<DeviceEntity>()
    val id: Int
    var nome: String
    var fkUserId: Int?
    var user: UserEntity

}

val Database.devices get() = this.sequenceOf(DevicesTable)
