package ru.filmapp.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("meow")
        }
    }
}
