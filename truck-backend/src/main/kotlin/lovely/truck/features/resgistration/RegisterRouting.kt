package lovely.truck.features.resgistration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import lovely.truck.features.login.LoginReceiveRemote
import lovely.truck.plugins.Test
import lovely.truck.utils.isValidEmail

fun Application.configureRegistrationRouting() {
    routing {
        post("/registration") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()
        }
    }
}