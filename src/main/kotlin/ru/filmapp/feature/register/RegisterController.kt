package ru.filmapp.feature.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.filmapp.database.tokens.TokenDTO
import ru.filmapp.database.tokens.Tokens
import ru.filmapp.database.users.UserDTO
import ru.filmapp.database.users.Users
import ru.filmapp.utils.isValidEqual
import java.util.*

class RegisterController (private val call: ApplicationCall){
    suspend fun registerNewUser(){
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()

        if(!registerReceiveRemote.email.isValidEqual()){
            call.respond(HttpStatusCode.BadRequest, "Email isn't valid")
        }
        val userDTO = Users.fetchUser(registerReceiveRemote.login)
        if(userDTO!=null){
            call.respond(HttpStatusCode.Conflict, "User is already exist")
        }
        else{
            val token=UUID.randomUUID().toString()
            Users.insert(
                UserDTO(
                    login=registerReceiveRemote.login,
                    password = registerReceiveRemote.password,
                    email=registerReceiveRemote.email,
                    username=""
                )
            )
            Tokens.insert(
                TokenDTO(
                    id=UUID.randomUUID().toString(),
                    login=registerReceiveRemote.login,
                    token=token
                )
            )

            call.respond(RegisterResponseRemote(token=token))
        }

    }
}