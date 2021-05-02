package name.shokred.popug_jira.account.create_operations

import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.account.CreditTaskOperation
import name.shokred.popug_jira.account.DebitTaskOperation
import name.shokred.popug_jira.account.Money
import name.shokred.popug_jira.account.Task
import name.shokred.popug_jira.account.port.LoadAccountPort
import name.shokred.popug_jira.account.port.OperationIdGenerator
import name.shokred.popug_jira.account.port.SaveAccountPort
import name.shokred.popug_jira.event.EventPublisher
import kotlin.random.Random

class CreateOperationsForTaskUseCase(
    private val operationIdGenerator: OperationIdGenerator,
    private val loadAccountPort: LoadAccountPort,
    private val saveAccountPort: SaveAccountPort,
    private val eventPublisher: EventPublisher
) : UseCase<CreateOperationsForTaskDto> {

    override fun invoke(dto: CreateOperationsForTaskDto) {
        val task = Task(dto.taskId)

        val account = (loadAccountPort.findByUserId(dto.userId)
            ?: throw IllegalArgumentException("Account for user [${dto.userId}] not found"))

        val debitTaskOperation = DebitTaskOperation(
            id = operationIdGenerator.generate(),
            cost = randomMoney(10, 20),
            account = account,
            subject = task
        )

        val creditTaskOperation = CreditTaskOperation(
            id = operationIdGenerator.generate(),
            cost = randomMoney(20, 40),
            account = account,
            subject = task
        )

        account.operations.add(debitTaskOperation)
        account.operations.add(creditTaskOperation)

        saveAccountPort.save(account)

        listOf(debitTaskOperation, creditTaskOperation)
            .forEach {
                eventPublisher.publish(CreateOperationsForTaskEvent(it.id, it.type))
            }
    }

    private fun randomMoney(lowerBound: Int, upperBound: Int): Money {
        return Money(Random.nextInt(lowerBound, upperBound + 1))
    }
}
