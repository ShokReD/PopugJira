package name.shokred.popug_jira.task.entity

import name.shokred.popug_jira.account.CreditTaskOperation
import name.shokred.popug_jira.account.DebitTaskOperation
import name.shokred.popug_jira.account.Money
import name.shokred.popug_jira.account.Operation
import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "OPERATIONS")
class OperationEntity(
    var cost: BigDecimal,
    var type: String,
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "subject_id")
    var subject: OperationSubjectEntity,
    var operationDate: OffsetDateTime
) : BaseEntity<Long>() {

    var accountId: Long = -1

    fun toDomain(): Operation {
        return when (type) {
            "debit" -> buildDebitOperation()
            "credit" -> buildCreditOperation()
            else -> throw IllegalStateException("Allowed only DebitTaskOperation.class or CreditTaskOperation.class")
        }
    }

    private fun buildDebitOperation(): DebitTaskOperation {
        return DebitTaskOperation(id!!, Money(cost), subject.toDomain())
    }

    private fun buildCreditOperation(): CreditTaskOperation {
        return CreditTaskOperation(id!!, Money(cost), subject.toDomain())
    }

    companion object {
        fun fromDomain(operation: Operation): OperationEntity {
            return OperationEntity(
                operation.cost.amount,
                when (operation) {
                    is DebitTaskOperation -> "DEBIT"
                    is CreditTaskOperation -> "CREDIT"
                    else -> throw IllegalStateException("Allowed only DebitTaskOperation.class or CreditTaskOperation.class")
                },
                OperationSubjectEntity.fromDomain(operation.subject),
                operation.operationDate()
            )
        }
    }
}
