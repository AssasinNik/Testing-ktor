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
           val receive = call.receive<LoginReceiveRemoteModel>()
            val first=InMemoryCache.userList.firstOrNull{it.login == receive.login}
            if(first==null){
                call.respond(HttpStatusCode.BadRequest, "User not found")
            }
            else{
                if(first.password==receive.password) {
                    val token = UUID.randomUUID().toString()
                    InMemoryCache.token.add(TokenCache(login = receive.login, token = token))
                    call.respond(LoginResponseRemoteModel(token = token))
                }
                else{
                    call.respond(HttpStatusCode.BadRequest, "Invalid Password")
                }
            }
        }
    }
}
