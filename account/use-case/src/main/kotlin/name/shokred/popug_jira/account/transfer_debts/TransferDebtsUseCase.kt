package name.shokred.popug_jira.account.transfer_debts

import name.shokred.popug_jira.EmptyUseCaseDto
import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.account.Account
import name.shokred.popug_jira.account.DebitTaskOperation
import name.shokred.popug_jira.account.Operation
import name.shokred.popug_jira.account.port.LoadOperationPort
import name.shokred.popug_jira.account.port.SaveOperationPort
import name.shokred.popug_jira.event.EventPublisher

class TransferDebtsUseCase(
    private val loadOperationPort: LoadOperationPort,
    private val saveOperationPort: SaveOperationPort,
    private val eventPublisher: EventPublisher
) : UseCase<EmptyUseCaseDto, Unit> {

    override fun invoke(dto: EmptyUseCaseDto) {
        loadOperationPort.findTodayOperation()
            .flatMap(Map.Entry<Account, List<Operation>>::value)
            .filterIsInstance<DebitTaskOperation>()
            .onEach(DebitTaskOperation::postponeForTomorrow)
            .onEach(saveOperationPort::save)
            .map(Operation::id)
            .let(::TransferDebtsEvent)
            .apply(eventPublisher::publish)
    }
}
