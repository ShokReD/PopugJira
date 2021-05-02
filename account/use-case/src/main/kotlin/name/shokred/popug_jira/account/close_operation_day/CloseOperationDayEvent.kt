package name.shokred.popug_jira.account.close_operation_day

import name.shokred.popug_jira.event.DomainEvent
import java.math.BigDecimal

data class CloseOperationDayEvent(
    val account: Long,
    val operations: List<Long>,
    val totalSum: BigDecimal
) : DomainEvent()
