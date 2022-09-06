package com.example.services

import com.example.models.UserModel
import com.example.repositories.UserRepository
import org.koin.java.KoinJavaComponent.inject

class UserServiceImpl : UserService {


    private val userRepository: UserRepository by inject(UserRepository::class.java)

    override fun getAllUsers(): List<UserModel>{
        return userRepository.getAllusers().map {
            UserModel.entityToObject(it)
        }
    }

    override fun getUser(id: Int): UserModel? {
        val user = userRepository.getUser(id)
        return user?.let { UserModel.entityToObject(it) }

    }

    override fun insertUser(user: UserModel): UserModel{
        val addedUser = userRepository.insertUser(user)
        return UserModel.entityToObject(addedUser)
    }

    override fun updateUser(user: UserModel, id: Int): UserModel? {
        val updatedUser = userRepository.updateUser(user, id)
        return updatedUser?.let { UserModel.entityToObject(it) }
    }

    override fun deleteUser(id: Int): UserModel? {
        val deletedUser = userRepository.deleteUser(id)
        return deletedUser?.let { UserModel.entityToObject(it) }
    }
}
