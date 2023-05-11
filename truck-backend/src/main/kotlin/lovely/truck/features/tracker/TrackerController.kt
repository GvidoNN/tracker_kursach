package lovely.truck.features.tracker

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import lovely.truck.database.trackers.TrackerDTO
import lovely.truck.database.trackers.Trackers
import org.jetbrains.exposed.exceptions.ExposedSQLException

class TrackerController(private val call: ApplicationCall) {

    suspend fun checkTracker() {
        val receive = call.receive<TrackerReceiveRemote>()
        val trackerDTO = Trackers.fetchTracker(receive.track_number)
        println("receive $trackerDTO")

        if (trackerDTO == null) {
            call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = "Tracker not found", done = false))
        } else {
            if (trackerDTO.track_number == receive.track_number) {
                print(trackerDTO)
                call.respond(TrackerResponseRemote(track_number = trackerDTO.track_number, truck = trackerDTO.truck, state = trackerDTO.state, date_start = trackerDTO.date_start.toString(), exception = "null", done = true))
            } else {
                //nothing
            }
        }
    }

    suspend fun addTracker(){
        val trackerReceiveRemote = call.receive<TrackerAddReceiveRemote>()
        val trackerDTO = Trackers.fetchTracker(trackerReceiveRemote.track_number)

        if (trackerDTO != null) {
            call.respond(TrackerResponseRemote(track_number = trackerDTO.track_number, truck = trackerDTO.truck, state = trackerDTO.state, date_start = trackerDTO.date_start.toString(), exception = "Tracker already exist ", done = false))
        } else {
            try {
                Trackers.insertTracker(
                    TrackerDTO(
                        track_number = trackerReceiveRemote.track_number,
                        truck = trackerReceiveRemote.truck,
                        state = trackerReceiveRemote.state,
                        date_start = trackerReceiveRemote.date_start
                    )
                )
                call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = "null", done = true))
            } catch (e: ExposedSQLException) {
                call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = "Tracker already exist ", done = false))
            } catch (e: Exception) {
                call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = e.localizedMessage, done = false))
            }
        }
    }

    suspend fun deleteTracker(){
        val receive = call.receive<TrackerReceiveRemote>()
        val trackerDTO = Trackers.deleteTracker(receive.track_number)
        println("receive $trackerDTO")
        try {
            Trackers.deleteTracker(receive.track_number)
            call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = "null", done = true))
        } catch(e: Exception) {
            call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = e.localizedMessage, done = false))
        }
    }

    suspend fun updateTracker(){
        val receive = call.receive<TrackerAddReceiveRemote>()
        val trackerDTO = Trackers.fetchTracker(receive.track_number)
        if(trackerDTO == null){
            call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = "Tracker not found", done = false))
        } else {
            try{
                Trackers.updateTracker(
                    TrackerDTO(
                        track_number = receive.track_number,
                        truck = receive.truck,
                        state = receive.state,
                        date_start = receive.date_start
                    )
                )
                call.respond(TrackerResponseRemote(track_number = receive.track_number, truck = receive.truck, state = receive.state, date_start = receive.date_start, exception = "null", done = true))
            } catch (e: Exception){
                call.respond(TrackerResponseRemote(track_number = "null", truck = "null", state = "null", date_start = "null", exception = e.localizedMessage, done = false))
            }
        }

    }

}