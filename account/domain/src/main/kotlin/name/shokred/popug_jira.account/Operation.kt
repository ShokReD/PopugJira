package name.shokred.popug_jira.account

import java.time.OffsetDateTime

interface Operation {
    val id: Long
    val cost: Money
    val type: String
    val subject: OperationSubject
    fun operationDate(): OffsetDateTime
}
