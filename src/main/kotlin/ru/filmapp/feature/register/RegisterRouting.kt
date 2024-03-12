package ru.filmapp.feature.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.filmapp.cache.InMemoryCache
import ru.filmapp.cache.TokenCache
import ru.filmapp.utils.isValidEqual
import java.util.UUID

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerController=RegisterController(call)
            registerController.registerNewUser()

        }
    }
}