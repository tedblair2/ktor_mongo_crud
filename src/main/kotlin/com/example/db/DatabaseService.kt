package com.example.db

import com.example.User
import org.bson.conversions.Bson

interface DatabaseService {
    suspend fun addUser(user: User)
    suspend fun updateUser(filter:Bson,updates: Bson)
    suspend fun deleteUser(user: User)
    suspend fun getUsers():List<User>
    suspend fun searchUser(query:String):List<User>
}