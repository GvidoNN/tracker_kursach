package lovely.truck.database.trackers

import org.joda.time.DateTime

data class TrackerDTO(
    val track_number: String,
    val truck: String,
    val state: String,
    val date_start: DateTime
)
