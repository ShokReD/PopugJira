package name.shokred.popug_jira.task.entity

import name.shokred.popug_jira.account.Account
import javax.persistence.*

@Entity
@Table(name = "ACCOUNTS")
class AccountEntity(
    var userId: Long
) : BaseEntity<Long>() {
    @OneToMany(
        mappedBy = "accountId",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    val operations: MutableCollection<OperationEntity> = mutableListOf()

    @PostPersist
    fun postPersist() {
        operations.forEach { it.accountId = this.id!! }
    }

    fun addOperation(operation: OperationEntity): AccountEntity {
        operations.add(operation)
        if (id != null) {
            operation.accountId = id!!
        }
        return this
    }

    fun toDomain(): Account {
        return Account(
            id!!,
            userId,
            operations.map(OperationEntity::toDomain).toMutableList()
        )
    }

    companion object {
        fun fromDomain(account: Account): AccountEntity {
            return account.operations
                .map(OperationEntity::fromDomain)
                .fold(AccountEntity(account.userId)) { acc, operation ->
                    acc.addOperation(operation)
                }
        }
    }
}
