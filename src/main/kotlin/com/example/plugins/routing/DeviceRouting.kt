package com.example.plugins.routing

import com.example.models.ApiResponseModel
import com.example.models.DeviceModel
import com.example.models.DeviceRequestModel
import com.example.services.DeviceService
import com.example.services.UserService
import com.example.utilities.BASE_DEVICE_API
import com.example.utilities.NON_ASSEGNATO
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.deviceRouting(){

    val deviceService: DeviceService by inject()
    val userService: UserService by inject()

    routing {

        /**
         * GET lista di tutti i device
         */
        get("$BASE_DEVICE_API/devices"){
            val devices = deviceService.getAllDevices()
            call.respond(HttpStatusCode.OK, ApiResponseModel(
                result = true,
                data = devices,
                message = null
            ))

        }

        /**
         * GET lista di device per ogni utente dal suo id
         */
        get(BASE_DEVICE_API){
            val userId = call.request.queryParameters["userId"]?.toInt() ?: -1
            if (userId != -1) {
                val devices = deviceService.getDevicesByUserId(userId)
                call.respond(
                    HttpStatusCode.OK, ApiResponseModel(
                        result = true,
                        data = devices,
                        message = null
                    )
                )

            } else {
                call.respond(
                    HttpStatusCode.BadRequest, ApiResponseModel(
                        result = false,
                        data = null,
                        message = "userId is required!"
                    )
                )
            }
        }

        /**
         * GET dettaglio di un device per id
         */
        get("$BASE_DEVICE_API/{deviceId}"){
            val deviceId = call.parameters["deviceId"]?.toInt() ?: -1

            val device = deviceService.getDeviceById(deviceId)
            if (device != null){
                call.respond(
                    HttpStatusCode.OK, ApiResponseModel(
                        result = true,
                        data = device,
                        message = null
                    )
                )
            }else{

                call.respond(
                    HttpStatusCode.NotFound, ApiResponseModel(
                        result = false,
                        data = "",
                        message = "Device with id $deviceId not found!"
                    )
                )
            }
        }

        /**
         * POST per inserire un nuovo dispositivo nel db
         */
        post("$BASE_DEVICE_API/insert"){
            val requestBody = call.receive<DeviceRequestModel>()
            val userProprietario = userService.getUser(requestBody.userIdProprietario)
            var userIdProprietario = NON_ASSEGNATO
            userProprietario?.let {
                userIdProprietario = it.id!!
            }
            val deviceToAdd = DeviceModel(id = null, nome = requestBody.nome, user = userProprietario)
            val device = deviceService.insertDevice(deviceToAdd, userIdProprietario)
            val deviceAdded = deviceService.getDeviceById(device.id!!)
            call.respond(HttpStatusCode.OK, ApiResponseModel(
                result = true,
                data = deviceAdded,
                message = "New device succesfully added!"
            ))
        }
    }
}
