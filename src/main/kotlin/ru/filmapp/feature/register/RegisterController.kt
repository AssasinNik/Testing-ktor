package ru.filmapp.feature.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.filmapp.cache.InMemoryCache
import ru.filmapp.cache.TokenCache
import ru.filmapp.database.users.Users
import java.util.*

class RegisterController {
    fun registerNewUser(registerReceiveRemote: RegisterReceiveRemote){
        val isUserExist = Users.fetchUser(registerReceiveRemote.login)
        if(InMemoryCache.userList.map{it.login}.contains(receive.login)){
            call.respond(HttpStatusCode.Conflict, "User is already exist")
        }

        val token= UUID.randomUUID().toString()
        InMemoryCache.userList.add(receive)
        InMemoryCache.token.add(TokenCache(login = receive.login, token=  token))

        call.respond(RegisterResponseRemote(token=token))
        /**/
    }
}