package com.example.plugins.routing

import com.example.models.ApiResponseModel
import com.example.models.UserModel
import com.example.models.UserRequestModel
import com.example.services.UserService
import com.example.utilities.BASE_USER_API
import com.example.utilities.NON_ASSEGNATO
import com.example.utilities.Utilities.capitalize
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.userRouting(){

    val userService: UserService by inject()

    routing {

        /**
         * GET per recuperare tutti gli utenti
         */
        get("$BASE_USER_API/users") {
            val users = userService.getAllUsers()
            call.respond(HttpStatusCode.OK, ApiResponseModel(
                result = true,
                data = users,
                message = null
            ))
        }

        /**
         * GET per recuperare i dettagli di un utente in base all'id
         */
        get("$BASE_USER_API/{id}"){
            val id = call.parameters["id"]!!.toInt()
            val user = userService.getUser(id)
            if (user?.id != null)
                call.respond(HttpStatusCode.OK, ApiResponseModel(
                    result = true,
                    data = user,
                    message = null))
            else
                call.respond(HttpStatusCode.NotFound, ApiResponseModel(
                    result = false,
                    data = "",
                    message = "User not found with id: $id"))
        }

        /**
         * POST per inserire nel db un nuovo utente
         */
        post("$BASE_USER_API/insert"){
            val requestBody = call.receive<UserRequestModel>()
            val user = UserModel(id = null, nome = requestBody.nome?.capitalize(), cognome = requestBody.cognome?.capitalize())
            val addedUser = userService.insertUser(user)
            call.respond(HttpStatusCode.OK, ApiResponseModel(
                result = true,
                data = addedUser,
                message = "New user succesfully added"))
        }

        /**
         * PUT per aggiornare un utente
         */
        put("$BASE_USER_API/update/{id}"){
            val id = call.parameters["id"]?.toInt() ?: -1
            val requestBody = call.receive<UserRequestModel>()
            val user = UserModel(id = id, nome = requestBody.nome?.capitalize(), cognome = requestBody.cognome?.capitalize())
            val updatedUser = userService.updateUser(user, id)
            if (updatedUser != null )
                call.respond(HttpStatusCode.OK, ApiResponseModel(
                    result = true,
                    data = updatedUser,
                    message = "User with id: $id succesfully updated!"))
            else
                call.respond(HttpStatusCode.NotFound, ApiResponseModel(
                    result = false,
                    data = "",
                    message = "User to update with id: $id not found!"))

        }

        /**
         * DELETE per rimuovere dal db un utente
         */
        delete("$BASE_USER_API/delete/{id}"){
            val id = call.parameters["id"]?.toInt() ?: -1
            if (id == NON_ASSEGNATO) {
                call.respond(HttpStatusCode.NotAcceptable, ApiResponseModel(
                    result = false,
                    data = "",
                    message = "User to delete with id $id not deletable!"
                ))
                return@delete
            }

            val userToDelete = userService.deleteUser(id)
            if ( userToDelete != null )
                call.respond(HttpStatusCode.OK, ApiResponseModel(
                    result = true,
                    data = userToDelete,
                    message = "User with id: $id succesfully deleted"
                ))
            else
                call.respond(HttpStatusCode.NotFound, ApiResponseModel(
                    result = false,
                    data = "",
                    message = "User to delete with id $id not found!"
                ))
        }


    }
}
