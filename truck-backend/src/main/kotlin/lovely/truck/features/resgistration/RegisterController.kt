package lovely.truck.features.resgistration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import lovely.truck.database.tokens.TokenDTO
import lovely.truck.database.tokens.Tokens
import lovely.truck.database.users.UserDTO
import lovely.truck.database.users.Users
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.UUID

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {

        val registrationReceiveRemote = call.receive<RegistrationReceiveRemote>()
        val userDTO = Users.fetchUser(registrationReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        login = registrationReceiveRemote.login,
                        password = registrationReceiveRemote.password,
                        email = registrationReceiveRemote.email,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.localizedMessage}")
            }

            Tokens.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(), login = registrationReceiveRemote.login,
                    token = token
                )
            )

            call.respond(RegistrationResponseRemote(token = token))
        }
    }
}