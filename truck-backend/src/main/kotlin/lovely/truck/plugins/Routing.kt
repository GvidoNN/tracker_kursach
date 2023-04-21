package lovely.truck.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val text: String
)


fun Application.configureRouting() {
    routing {
        get("/hello") {
//            call.respondText("Hello World!")
            call.respond(listOf(
                Test(text = "Hello World!"),
                Test(text = "Hello World2!")
            ))
        }
    }
}
