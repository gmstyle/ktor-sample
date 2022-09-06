package com.example.repositories

import com.example.database.DatabaseConnector.database
import com.example.models.UserModel
import com.example.tables.UserEntity
import com.example.tables.users
import org.ktorm.dsl.eq
import org.ktorm.entity.*

class UserRepository {

    /**
     * Read all from db
     */
    fun getAllusers(): List<UserEntity> {
        return database.users.toList()

    }

    /**
     * Read one User from db
     */
    fun getUser(id: Int): UserEntity? {
        return database.users.find {
            it.id eq id
        }
    }

    /**
     * Insert in db
     * la funzione insert ritorna il numero di righe inserite (In questo caso un User)
     * se l'utente è inserito correttamente ritorna 1.
     */

    fun insertUser(user: UserModel): UserEntity {
        val userToAdd = UserEntity{
            nome = user.nome.toString()
            cognome = user.cognome.toString()

        }
        database.users.add(userToAdd)
        return userToAdd

    }

    /**
     * Update in db
     */
    fun updateUser(user: UserModel, id: Int): UserEntity? {

        val userToUpdate =  database.users.firstOrNull {
            it.id eq id
        }?.apply {
            this.nome = user.nome.toString()
            this.cognome = user.cognome.toString()
            database.users.update(this)
        }
        return userToUpdate
    }

    /**
     * Delete from db
     */
    fun deleteUser(id: Int): UserEntity? {
        val userToDelete = database.users
            .firstOrNull{
                it.id eq id
            }?.apply { this.delete() }
        return userToDelete
    }

}
