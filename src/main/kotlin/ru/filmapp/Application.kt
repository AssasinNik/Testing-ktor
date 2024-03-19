package ru.filmapp

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.filmapp.feature.login.configureLoginRouting
import ru.filmapp.feature.register.configureRegisterRouting
import ru.filmapp.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://127.0.0.1:5432/film_app", driver="org.postgresql.Driver",  user = "postgres", password = "*******")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}
