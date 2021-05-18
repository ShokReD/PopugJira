package name.shokred.popug_jira.account

import java.time.OffsetDateTime

/**
 * То что мы должны выплатить
 */
class CreditTaskOperation(
    override val id: Long,
    override val cost: Money,
    override val subject: OperationSubject
) : Operation {

    override val type: String = "credit"
    private var operationDate: OffsetDateTime = OffsetDateTime.now()
    private var status: CreditOperationStatus = CreditOperationStatus.NOT_COMPLETED

    fun isCancelled(): Boolean {
        return this.status == CreditOperationStatus.CANCELLED
    }

    fun isCompleted(): Boolean {
        return this.status == CreditOperationStatus.COMPLETED
    }

    fun complete() {
        if (isCancelled()) {
            throw IllegalStateException("Unable to complete a cancelled operation")
        }
        this.status = CreditOperationStatus.COMPLETED
    }

    fun cancel() {
        if (isCompleted()) {
            throw IllegalStateException("Unable to cancel a completed operation")
        }
        this.status = CreditOperationStatus.CANCELLED
    }

    override fun operationDate(): OffsetDateTime {
        return this.operationDate
    }

    private enum class CreditOperationStatus {
        NOT_COMPLETED,
        COMPLETED,
        CANCELLED
    }
}
