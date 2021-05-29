package name.shokred.popug_jira.task.adapter

import name.shokred.popug_jira.account.*
import name.shokred.popug_jira.account.port.LoadOperationPort
import name.shokred.popug_jira.task.configuration.JpaConfiguration
import name.shokred.popug_jira.task.entity.AccountEntity
import name.shokred.popug_jira.task.entity.OperationEntity
import name.shokred.popug_jira.task.entity.OperationSubjectEntity
import name.shokred.popug_jira.task.repository.AccountRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import java.math.BigDecimal
import java.time.OffsetDateTime

@DataJpaTest
@ContextConfiguration(classes = [JpaConfiguration::class])
@Import(LoadOperationAdapter::class)
internal class LoadOperationAdapterTest {

    @Autowired
    private lateinit var loadOperationPort: LoadOperationPort

    @Autowired
    private lateinit var accountRepository: AccountRepository

    private val now = OffsetDateTime.now()

    @Test
    fun findByTaskId() {
        val account = AccountEntity(17)
            .addOperation(
                OperationEntity(
                    BigDecimal(107),
                    "debit",
                    OperationSubjectEntity(17, "task"),
                    now
                )
            )
            .addOperation(
                OperationEntity(
                    BigDecimal(120),
                    "credit",
                    OperationSubjectEntity(17, "task"),
                    now.plusMinutes(1)
                )
            )

        accountRepository.save(account)
        val actualOperations: List<Operation> = loadOperationPort.findByTaskId(17)
        val expectedOperations: List<Operation> = listOf(
            DebitTaskOperation(1, Money(107), Task(17)),
            CreditTaskOperation(2, Money(120), Task(17))
        )

        Assertions.assertEquals(expectedOperations, actualOperations)
    }

    @Test
    fun findTodayOperation() {
        val account1 = AccountEntity(17)
            .addOperation(
                OperationEntity(
                    BigDecimal(107),
                    "debit",
                    OperationSubjectEntity(17, "task"),
                    now.minusDays(2)
                )
            )
            .addOperation(
                OperationEntity(
                    BigDecimal(121),
                    "credit",
                    OperationSubjectEntity(12, "task"),
                    now.plusMinutes(1)
                )
            )

        val account2 = AccountEntity(18)
            .addOperation(
                OperationEntity(
                    BigDecimal(10),
                    "credit",
                    OperationSubjectEntity(17, "task"),
                    now.plusWeeks(1)
                )
            )
            .addOperation(
                OperationEntity(
                    BigDecimal(3),
                    "debit",
                    OperationSubjectEntity(31, "task"),
                    now
                )
            )

        listOf(account1, account2)
            .forEach(accountRepository::save)

        val actualTodayOperations = loadOperationPort.findTodayOperation()

        val expectedAccount1 = Account(
            1,
            17,
            mutableListOf(
                DebitTaskOperation(1, Money(107), Task(17)),
                CreditTaskOperation(2, Money(121), Task(12))
            )
        )

        val expectedAccount2 = Account(
            2,
            18,
            mutableListOf(
                CreditTaskOperation(3, Money(10), Task(17)),
                DebitTaskOperation(4, Money(3), Task(31))
            )
        )

        val expectedTodayOperations = mapOf(
            expectedAccount1 to listOf(CreditTaskOperation(2, Money(121), Task(12))),
            expectedAccount2 to listOf(DebitTaskOperation(4, Money(3), Task(31)))
        )
        Assertions.assertEquals(expectedTodayOperations, actualTodayOperations)
    }
}
