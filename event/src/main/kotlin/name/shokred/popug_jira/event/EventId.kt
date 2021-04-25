package name.shokred.popug_jira.event

import java.time.OffsetDateTime
import java.util.*

data class EventId internal constructor(val id: UUID, val datetime: OffsetDateTime) {
    companion object {
        fun generate(): EventId = EventId(UUID.randomUUID(), OffsetDateTime.now())
    }
}
