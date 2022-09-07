package com.example.services

import com.example.models.AccountModel
import com.example.repositories.AccountRepository
import org.koin.java.KoinJavaComponent.inject

class AcccountServiceImpl : AccountService {

    private val accountRepository: AccountRepository by inject(AccountRepository::class.java)

    override fun getAccountByUsernameAndPassword(username: String, password: String): AccountModel? {

        val account = accountRepository.getAccountByUsernameAndPassword(username, password)
            return account?.let {
                AccountModel.entityToObject(it)
            }

    }

}
