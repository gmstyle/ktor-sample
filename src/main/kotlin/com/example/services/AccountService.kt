package com.example.services

import com.example.models.AccountModel

interface AccountService {

    fun getAccountByUsernameAndPassword(username: String, password: String): AccountModel?
}
