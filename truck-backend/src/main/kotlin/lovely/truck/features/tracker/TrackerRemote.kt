package lovely.truck.features.tracker

import kotlinx.serialization.Serializable


@Serializable
data class TrackerReceiveRemote(
    val track_number: String
)

@Serializable
data class TrackerResponseRemote(
    val track_number: String,
    val truck: String,
    val state: String,
    val date_start: String,
    val exception: String,
    val tracking: Boolean
)
