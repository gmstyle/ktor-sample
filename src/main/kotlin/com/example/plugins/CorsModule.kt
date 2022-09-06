package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureCorse(){
    install(CORS){
        //hosts.add("http://localhost:4200")
        anyHost()
        methods.apply {
            add(HttpMethod.Get)
            add(HttpMethod.Post)
            add(HttpMethod.Put)
            add(HttpMethod.Delete)
        }
        //header(HttpHeaders.ContentType)
    }
}
