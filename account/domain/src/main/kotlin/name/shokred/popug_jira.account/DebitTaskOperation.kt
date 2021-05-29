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


    override fun toString(): String {
        return "DebitTaskOperation(id=$id, cost=$cost, subject=$subject, type='$type', operationDate=$operationDate)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DebitTaskOperation) return false

        if (id != other.id) return false
        if (cost != other.cost) return false
        if (subject != other.subject) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + cost.hashCode()
        result = 31 * result + subject.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}
