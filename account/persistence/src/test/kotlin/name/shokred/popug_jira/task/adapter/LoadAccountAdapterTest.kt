package name.shokred.popug_jira.task.adapter

import name.shokred.popug_jira.account.*
import name.shokred.popug_jira.account.port.LoadAccountPort
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
@Import(LoadAccountAdapter::class)
internal class LoadAccountAdapterTest {

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var loadAccountPort: LoadAccountPort

    private val now = OffsetDateTime.now()

    @Test
    fun test() {
        accountRepository.save(
            AccountEntity(17)
                .addOperation(
                    OperationEntity(
                        BigDecimal(2000),
                        "debit",
                        OperationSubjectEntity(33, "task"),
                        now
                    )
                )
                .addOperation(
                    OperationEntity(
                        BigDecimal(1000),
                        "credit",
                        OperationSubjectEntity(33, "task"),
                        now.plusMinutes(1)
                    )
                )
        )

        val actualAccount = loadAccountPort.findByUserId(17)
        val expectedAccount = Account(
            id = 1,
            17,
            mutableListOf(
                DebitTaskOperation(1, Money(2000), Task(33)),
                CreditTaskOperation(2, Money(1000), Task(33))
            )
        )

        Assertions.assertEquals(expectedAccount, actualAccount)
    }
}
