package com.example.plugins

import com.example.User
import com.example.UserController
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userController by inject<UserController>()
    routing {
        post("/") {
            val user=call.receive<User>()
            userController.addUser(user)
            call.respond(HttpStatusCode.OK)
        }
        put("/") {
            val user=call.receive<User>()
            val filter=Filters.eq("name",user.name)
            val updateparams=Updates.combine(
                Updates.set("name",user.name),
                Updates.set("age",user.age),
                Updates.set("address",user.address)
            )
            userController.updateUser(filter,updateparams)
            call.respondText("Update successful")
        }
        delete("/") {
            val user=call.receive<User>()
            userController.deleteUser(user)
            call.respondText("Deleted successfully")
        }
        get("/") {
            val users=userController.getUsers()
            call.respond(HttpStatusCode.OK,users)
        }
        get("/search") {
            val query=call.request.queryParameters["query"].toString()
            val list=userController.searchUser(query)
            call.respond(HttpStatusCode.OK,list)
        }
    }
}
