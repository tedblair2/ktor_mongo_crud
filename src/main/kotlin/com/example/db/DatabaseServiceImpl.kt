package com.example.db

import com.example.User
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Indexes
import com.mongodb.client.model.TextSearchOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.toList
import org.bson.conversions.Bson
import java.util.regex.Pattern

class DatabaseServiceImpl(private val db:MongoDatabase) : DatabaseService {
    private val usersCollection=db.getCollection<User>(collectionName = "users")

    override suspend fun addUser(user: User) {
        usersCollection.insertOne(user)
    }

    override suspend fun updateUser(filter:Bson,updates: Bson) {
        usersCollection.updateOne(filter = filter, update = updates)
    }

    override suspend fun deleteUser(user: User) {
        val filter=Filters.eq("name",user.name)
        usersCollection.deleteOne(filter)
    }

    override suspend fun getUsers(): List<User> {
        return usersCollection.find<User>().toList()
    }

    override suspend fun searchUser(query: String): List<User> {
        val filter=Filters.regex("name",query,"i")
        return usersCollection.find<User>(filter).toList()
    }
}