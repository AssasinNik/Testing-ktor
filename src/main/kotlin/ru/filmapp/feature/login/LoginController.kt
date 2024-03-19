package ru.filmapp.feature.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.filmapp.database.tokens.TokenDTO
import ru.filmapp.database.tokens.Tokens
import ru.filmapp.database.users.Users
import java.util.*

class LoginController(private val call: ApplicationCall) {
    suspend fun PerformLogin(){
        val receive = call.receive<LoginReceiveRemoteModel>()

        val userDTO= Users.fetchUser(receive.login)

        if(userDTO==null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        }
        else{
            if(userDTO.password==receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )
                call.respond(LoginResponseRemoteModel(token=token))
            }
            else run {
                    call.respond(HttpStatusCode.BadRequest, "Invalid Password")
            }
        }
    }
}