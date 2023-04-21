package lovely.truck

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import lovely.truck.features.login.configureLoginRouting
import lovely.truck.features.resgistration.configureRegistrationRouting
import lovely.truck.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/truck",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "123aa123aa123aa"
    )
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegistrationRouting()
    configureSerialization()
}
