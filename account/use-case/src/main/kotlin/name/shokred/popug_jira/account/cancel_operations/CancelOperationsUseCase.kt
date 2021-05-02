package name.shokred.popug_jira.account.cancel_operations

import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.account.CreditTaskOperation
import name.shokred.popug_jira.account.Operation
import name.shokred.popug_jira.account.port.LoadOperationPort
import name.shokred.popug_jira.account.port.SaveOperationPort
import name.shokred.popug_jira.event.EventPublisher

class CancelOperationsUseCase(
    private val loadOperationPort: LoadOperationPort,
    private val saveOperationPort: SaveOperationPort,
    private val eventPublisher: EventPublisher
) : UseCase<CancelOperationsDto> {

    override fun invoke(dto: CancelOperationsDto) {
        val operations = loadOperationPort.findByTaskId(dto.taskId)

        operations.filterIsInstance<CreditTaskOperation>()
            .onEach(CreditTaskOperation::cancel)
            .onEach(saveOperationPort::save)
            .map(Operation::id)
            .let(::CancelOperationsEvent)
            .apply(eventPublisher::publish)
    }
}
