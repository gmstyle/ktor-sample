package com.example.plugins.routing

import com.example.models.AccountModel
import com.example.models.ApiResponseModel
import com.example.models.LoginRequestModel
import com.example.services.AccountService
import com.example.utilities.BASE_ACCOUNT_API
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.accountRouting() {

    val accountService: AccountService by inject()

    routing {

        /**
         * POST login account utente
         */
        post("$BASE_ACCOUNT_API/login") {
            val requestBody = call.receive<LoginRequestModel>()
            val account = accountService.getAccountByUsernameAndPassword(requestBody.username, requestBody.password)
            if (account != null) {
                call.respond(HttpStatusCode.OK, ApiResponseModel(
                    result = true,
                    data = account,
                    message = "Login success"
                ))
            } else {
                call.respond(HttpStatusCode.NotFound, ApiResponseModel(
                    result = false,
                    data = "",
                    message = "Account not found"))
            }
        }

    }

}
