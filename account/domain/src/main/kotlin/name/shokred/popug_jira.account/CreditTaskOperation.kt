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


    override fun toString(): String {
        return "CreditTaskOperation(id=$id, cost=$cost, subject=$subject, type='$type', operationDate=$operationDate, status=$status)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CreditTaskOperation) return false

        if (id != other.id) return false
        if (cost != other.cost) return false
        if (subject != other.subject) return false
        if (type != other.type) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + cost.hashCode()
        result = 31 * result + subject.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }

    private enum class CreditOperationStatus {
        NOT_COMPLETED,
        COMPLETED,
        CANCELLED
    }
}
