package lovely.truck.features.login

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import lovely.truck.database.tokens.TokenDTO
import lovely.truck.database.tokens.Tokens
import lovely.truck.database.users.Users
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.login)
        println("receive ${userDTO}")

        if (userDTO == null) {
            call.respond(LoginResponseRemote(token = "null", logIn = false, exception = "user not found"))
        } else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token,
                    )
                )

                call.respond(LoginResponseRemote(token = token, logIn = true, exception = "null"))
            } else {
                call.respond(LoginResponseRemote(token = "null", logIn = false, exception = "invalid password"))
            }
        }
    }
}