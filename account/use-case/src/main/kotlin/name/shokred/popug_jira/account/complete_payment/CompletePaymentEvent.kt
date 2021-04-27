package name.shokred.popug_jira.account.complete_payment

import name.shokred.popug_jira.event.DomainEvent

data class CompletePaymentEvent(val operationIds: List<Long>) : DomainEvent()
