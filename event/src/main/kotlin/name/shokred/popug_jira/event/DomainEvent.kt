package name.shokred.popug_jira.event

open class DomainEvent protected constructor() {
    val id: EventId = EventId.generate()
}
