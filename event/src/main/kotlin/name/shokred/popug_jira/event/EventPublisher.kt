package name.shokred.popug_jira.event

interface EventPublisher {

    fun publish(event: DomainEvent)
}