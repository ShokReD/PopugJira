package name.shokred.popug_jira.account.cancel_operations

import name.shokred.popug_jira.event.DomainEvent

data class CancelOperationsEvent(val operationIds: List<Long>) : DomainEvent()
