package com.example.di

import com.example.UserController
import com.example.db.DatabaseService
import com.example.db.DatabaseServiceImpl
import com.mongodb.kotlin.client.coroutine.MongoClient
import org.koin.dsl.module

val mainModule= module {
    single {
        MongoClient.create().getDatabase(databaseName = "another_users_db")
    }
    single<DatabaseService> {
        DatabaseServiceImpl(get())
    }
    single {
        UserController(get())
    }
}