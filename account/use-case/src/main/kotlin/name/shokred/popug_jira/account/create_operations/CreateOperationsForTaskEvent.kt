package name.shokred.popug_jira.account.create_operations

import name.shokred.popug_jira.event.DomainEvent

data class CreateOperationsForTaskEvent(
    val operationId: Long,
    val operationType: String
) : DomainEvent()
