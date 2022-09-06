package com.example.plugins.routing

import com.example.utilities.BASE_URL
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.defaultRouting(){

    routing {
        /**
         * GET per rispondere con una pagina HTML
         */
        get(BASE_URL){
            val indexPage = javaClass.getResource("/web/index.html")?.readText()
            call.respondText(indexPage!!, ContentType.Text.Html)
        }

        /**
         * Inserire HEADERS personalizzati in una risposta
         */
        get("$BASE_URL/headers"){
            call.response.headers
                .apply {
                    append(name = "server-name", value = "KtorServer")
                    append(name = "server-content", value = "Users")
                }
            call.respond("Headers attached")
        }

        /**
         * GET scaricare un file dalle resources
         */
        get("$BASE_URL/download"){
            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName, "image.png"
                ).toString()
            )
            val file = File(javaClass.getResource("/files/hero40961.png")!!.path)
            call.respondFile(file)
        }

        /**
         * GET con parametri in url (queryParameters)
         * http://0.0.0.0:8090/api/v1/parametri?nome=Gabriele&cognome=Martina
         */
        get("$BASE_URL/parametri"){
            val nome = call.request.queryParameters["nome"]
            val cognome = call.request.queryParameters["cognome"]
            call.respond("Nome: $nome e Cognome: $cognome")
        }
    }

}
