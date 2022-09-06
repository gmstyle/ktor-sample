package com.example.services

import com.example.models.UserModel

interface UserService {
    fun getAllUsers(): List<UserModel>
    fun getUser(id: Int): UserModel?
    fun insertUser(user: UserModel): UserModel
    fun updateUser(user: UserModel, id: Int): UserModel?
    fun deleteUser(id: Int): UserModel?
}
