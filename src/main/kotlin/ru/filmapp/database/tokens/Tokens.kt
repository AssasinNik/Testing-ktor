package ru.filmapp.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("tokens"){
    private val login=Tokens.varchar("login", 55)
    private val id=Tokens.varchar("id", 55)
    private val token =Tokens.varchar("token", 55)

    fun insert(tokenDTO: TokenDTO){
        transaction{
            Tokens.insert{
                it[id] = tokenDTO.id
                it[login]=tokenDTO.login
                it[token]=tokenDTO.token
            }
        }
    }
}