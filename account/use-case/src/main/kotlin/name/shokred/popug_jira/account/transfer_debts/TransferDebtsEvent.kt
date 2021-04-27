package name.shokred.popug_jira.account.transfer_debts

import name.shokred.popug_jira.event.DomainEvent

data class TransferDebtsEvent(val operationIds: List<Long>) : DomainEvent()
