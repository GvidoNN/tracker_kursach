package lovely.truck.features.tracker

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import lovely.truck.database.trackers.Trackers

class TrackerController(private val call: ApplicationCall) {

    suspend fun checkTracker() {
        val receive = call.receive<TrackerReceiveRemote>()
        val trackerDTO = Trackers.fetchTracker(receive.track_number)
        println("receive $trackerDTO")

        if (trackerDTO == null) {
            call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = "Tracker not found", tracking = false))
        } else {
            if (trackerDTO.track_number == receive.track_number) {
                print(trackerDTO)
                call.respond(TrackerResponseRemote(track_number = trackerDTO.track_number, truck = trackerDTO.truck, state = trackerDTO.state, date_start = trackerDTO.date_start.toString(), exception = "null", tracking = true))
            } else {
//                call.respond(LoginResponseRemote(token = "null", logIn = false, exception = "invalid password"))
            }
        }


    }


}