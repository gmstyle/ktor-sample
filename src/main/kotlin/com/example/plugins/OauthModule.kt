package com.example.plugins

import com.example.models.UserSessionModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.sessions.*


val httpClient = HttpClient(CIO){
    install(ContentNegotiation){
        json()
    }
}

fun Application.oauthModule() {
    install(Sessions) {
        cookie<UserSessionModel>("user_session")
    }

    install(Authentication) {
        oauth("auth-oauth-google") {
            urlProvider = { "http://localhost:8090/callback" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
                    requestMethod = HttpMethod.Post,
                    //clientId = System.getenv("GOOGLE_CLIENT_ID"),
                    //clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
                    clientId = "624185253610-97e97ht71057aaeplvqt2b84ohsf3o6h.apps.googleusercontent.com",
                    clientSecret = "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",
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile"),
                    extraTokenParameters = listOf("access_type" to "offline")
                )
            }
            client = httpClient
        }
    }
}
