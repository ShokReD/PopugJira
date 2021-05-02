package name.shokred.popug_jira.task.complete_task

import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.event.EventPublisher
import name.shokred.popug_jira.task.port.LoadTaskPort
import name.shokred.popug_jira.task.port.UpdateTaskPort

class CompleteTaskUseCase(
    private val loadTaskPort: LoadTaskPort,
    private val updateTaskPort: UpdateTaskPort,
    private val eventPublisher: EventPublisher
) : UseCase<CompleteTaskDto, Unit> {

    override fun invoke(dto: CompleteTaskDto) {
        val task = (loadTaskPort.findById(dto.taskId)
            ?: throw IllegalArgumentException("Task [${dto.taskId}] not found"))

        task.complete()
        updateTaskPort.update(task)
        eventPublisher.publish(CompleteTaskEvent(task.id))
    }
}
