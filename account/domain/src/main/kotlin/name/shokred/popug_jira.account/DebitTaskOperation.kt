package name.shokred.popug_jira.account

import java.time.OffsetDateTime

/**
 * То что нам должны выплатить
 */
class DebitTaskOperation(
    override val id: Long,
    override val cost: Money,
    override val subject: OperationSubject
) : Operation {

    override val type: String = "debit"
    private var operationDate: OffsetDateTime = OffsetDateTime.now()

    fun postponeForTomorrow() {
        this.operationDate = this.operationDate.plusDays(1)
    }

    override fun operationDate(): OffsetDateTime {
        return this.operationDate
    }
}
