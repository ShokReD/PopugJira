package name.shokred.popug_jira.account.close_operation_day

import name.shokred.popug_jira.EmptyUseCaseDto
import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.account.Account
import name.shokred.popug_jira.account.DebitTaskOperation
import name.shokred.popug_jira.account.Money
import name.shokred.popug_jira.account.Operation
import name.shokred.popug_jira.account.port.LoadOperationPort
import name.shokred.popug_jira.account.transfer_debts.TransferDebtsUseCase
import name.shokred.popug_jira.event.EventPublisher

class CloseOperationDayUseCase(
    private val loadOperationPort: LoadOperationPort,
    private val transferDebtsUseCase: TransferDebtsUseCase,
    private val eventPublisher: EventPublisher
) : UseCase<EmptyUseCaseDto, Unit> {

    override fun invoke(dto: EmptyUseCaseDto) {
        transferDebtsUseCase.invoke(EmptyUseCaseDto())

        loadOperationPort.findTodayOperation()
            .map(this::createEvent)
            .forEach(eventPublisher::publish)
    }

    private fun createEvent(entry: Map.Entry<Account, List<Operation>>): CloseOperationDayEvent {
        val account = entry.key
        val operations = entry.value
        return CloseOperationDayEvent(
            account.userId,
            operations.map(Operation::id),
            operations.totalSum().amount
        )
    }

    private fun List<Operation>.totalSum(): Money {
        fun Operation.currentCost(): Money {
            return when (this) {
                is DebitTaskOperation -> -this.cost
                else -> this.cost
            }
        }

        return this.map(Operation::currentCost)
            .reduce(Money::plus)
    }
}
