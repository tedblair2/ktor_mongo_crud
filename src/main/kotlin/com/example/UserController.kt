package com.example

import com.example.db.DatabaseService
import org.bson.conversions.Bson

class UserController(private val databaseService: DatabaseService) {

    suspend fun addUser(user: User){
        databaseService.addUser(user)
    }

    suspend fun updateUser(filter:Bson,update:Bson){
        databaseService.updateUser(filter,update)
    }

    suspend fun deleteUser(user: User){
        databaseService.deleteUser(user)
    }
    suspend fun getUsers():List<User> = databaseService.getUsers()

    suspend fun searchUser(query:String):List<User> = databaseService.searchUser(query)
}