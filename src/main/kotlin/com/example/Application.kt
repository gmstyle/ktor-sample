package com.example

import com.example.plugins.*
import com.example.plugins.routing.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

/*
fun main() {
    embeddedServer(Netty, port = 8090, host = "0.0.0.0") {
        configureMonitoring()
        configureSerialization()
        configureSecurity()
        configKoinModules()
        configureCorse()
        sampleRouting()
        userRouting()
        deviceRouting()
        defaultRouting()
    }.start(wait = true)
}
*/

fun main(args: Array<String>): Unit =
    EngineMain.main(args)

// configurazione in resources/application.conf
fun Application.module() {

    // client http necessario per oauth2
    val applicationHttpClient = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }

    environment.config.propertyOrNull("ktor.deployment.port")?.getString()
    configureMonitoring()
    configureSerialization()
    configureSecurity()
    configKoinModules()
    configureCorse()
    oauthRouting(applicationHttpClient)
    userRouting()
    deviceRouting()
    defaultRouting()
    accountRouting()
    oauthModule(applicationHttpClient)
}
