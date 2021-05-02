package name.shokred.popug_jira.account.complete_payment

import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.account.CreditTaskOperation
import name.shokred.popug_jira.account.Operation
import name.shokred.popug_jira.account.port.LoadOperationPort
import name.shokred.popug_jira.account.port.SaveOperationPort
import name.shokred.popug_jira.event.EventPublisher

class CompletePaymentUseCase(
    private val loadOperationPort: LoadOperationPort,
    private val saveOperationPort: SaveOperationPort,
    private val eventPublisher: EventPublisher
) : UseCase<CompletePaymentDto, Unit> {

    override fun invoke(dto: CompletePaymentDto) {
        val operations = loadOperationPort.findByTaskId(dto.taskId)

        if (operations.isEmpty()) {
            throw IllegalStateException("Operations for task [${dto.taskId}] not found")
        }

        operations.filterIsInstance<CreditTaskOperation>()
            .onEach(CreditTaskOperation::complete)
            .forEach(saveOperationPort::save)

        operations.map(Operation::id)
            .let(::CompletePaymentEvent)
            .apply(eventPublisher::publish)
    }
}
