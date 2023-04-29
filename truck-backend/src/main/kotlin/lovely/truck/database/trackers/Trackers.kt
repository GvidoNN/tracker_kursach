package lovely.truck.database.trackers

import kotlinx.datetime.toJavaLocalDate
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.datetime
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Timestamp
import java.time.LocalDateTime

object Trackers: Table("trackers") {

    private val trackNumber = Trackers.varchar("track_number",20)
    private val truck = Trackers.varchar("truck",50)
    private val state = Trackers.varchar("state",20)
    private val dateStart = Trackers.varchar("date_start",10)

    fun insertTracker(trackerDTO: TrackerDTO){
        transaction {
            Trackers.insert{
                it[trackNumber] = trackerDTO.track_number
                it[truck] = trackerDTO.truck
                it[state] = trackerDTO.state
                it[dateStart] = trackerDTO.date_start

            }
        }
    }

    fun updateTracker(trackerDTO: TrackerDTO){
        transaction {
            Trackers.update({Trackers.trackNumber eq trackerDTO.track_number}){
                it[truck] = trackerDTO.truck
                it[state] = trackerDTO.state
                it[dateStart] = trackerDTO.date_start
            }
        }
    }

    fun fetchTracker(tracker: String): TrackerDTO? {

        return try {
            transaction {
                val trackerModel = Trackers.select {Trackers.trackNumber.eq(tracker)}.single()
                TrackerDTO(
                    track_number = trackerModel[Trackers.trackNumber],
                    truck = trackerModel[Trackers.truck],
                    state = trackerModel[Trackers.state],
                    date_start = trackerModel[Trackers.dateStart])
            }
        } catch (e: Exception){
            null
        }
    }

    fun deleteTracker(tracker: String){
        transaction {
            Trackers.deleteWhere { Trackers.trackNumber eq tracker }
        }
    }
}