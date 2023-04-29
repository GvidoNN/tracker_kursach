package lovely.truck.features.tracker

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureTrackerRouting() {
    routing {
        post("/checktracker") {
            val trackerController = TrackerController(call)
            trackerController.checkTracker()
        }

        post("/addtracker") {
            val trackerController = TrackerController(call)
            trackerController.addTracker()
        }

        post("/deletetracker"){
            val trackerController = TrackerController(call)
            trackerController.deleteTracker()
        }

        post ("/updatetracker"){
            val trackerController = TrackerController(call)
            trackerController.updateTracker()
        }
    }
}