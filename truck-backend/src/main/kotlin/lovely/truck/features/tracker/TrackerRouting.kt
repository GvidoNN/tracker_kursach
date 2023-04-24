package lovely.truck.features.tracker

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureTrackerRouting() {
    routing {
        post("/checktracker") {
            val trackerController = TrackerController(call)
            trackerController.checkTracker()
        }
    }
}