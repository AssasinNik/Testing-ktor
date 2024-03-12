package ru.filmapp.feature.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.filmapp.cache.InMemoryCache
import ru.filmapp.cache.TokenCache
import ru.filmapp.feature.register.RegisterReceiveRemote
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
           val loginController=LoginController(call)
            loginController.PerformLogin()
        }
    }
}
