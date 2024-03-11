package ru.filmapp.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table("users"){
    private val login=Users.varchar("login", 25)
    private val password=Users.varchar("password", 25)
    private val username=Users.varchar("username", 30)
    private val email=Users.varchar("email", 25)

    fun insert(userDTO: UserDTO){
        transaction{
            Users.insert{
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[email] = userDTO.email ?: ""
                it[username] = userDTO.username
            }
        }
    }
    fun fetchUser(login:String):UserDTO{
        val userModel = Users.select{Users.login.eq(login)}.single()
        return UserDTO(
            login = userModel[Users.login],
            password = userModel[Users.password],
            email = userModel[Users.email],
            username = userModel[Users.username],
        )
    }
}