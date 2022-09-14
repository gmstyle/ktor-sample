package com.example.plugins.routing

import com.example.models.UserInfo
import com.example.models.UserSessionModel
import com.example.plugins.httpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.p

fun Application.oauthRouting() {

    routing {
        /**
         * OAUTH login
         */
        authenticate("auth-oauth-google") {
            get("/login") {
                // Redirects to 'authorizeUrl' automatically
            }

            get("/callback") {
                val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
                call.sessions.set(UserSessionModel(principal?.accessToken.toString()))
                call.respondRedirect("/hello")
            }
        }
        get("/") {
            call.respondHtml {
                body {
                    p {
                        a("/login") { +"Login with Google" }
                    }
                }
            }
        }
        get("/hello") {
            val userSession: UserSessionModel? = call.sessions.get()
            if (userSession != null) {
                val userInfo: UserInfo = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer ${userSession.token}")
                    }
                }.body()
                call.respondText("Hello, ${userInfo.name}!")
            } else {
                call.respondRedirect("/")
            }
        }
    }
}
