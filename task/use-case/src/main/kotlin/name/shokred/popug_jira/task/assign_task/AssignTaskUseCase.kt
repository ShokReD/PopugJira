package name.shokred.popug_jira.task.assign_task

import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.event.EventPublisher
import name.shokred.popug_jira.task.port.LoadTaskPort
import name.shokred.popug_jira.task.port.LoadUserPort
import name.shokred.popug_jira.task.port.UpdateTaskPort

class AssignTaskUseCase(
    private val loadTaskPort: LoadTaskPort,
    private val loadUserPort: LoadUserPort,
    private val updateTaskPort: UpdateTaskPort,
    private val eventPublisher: EventPublisher
) : UseCase<AssignTaskDto> {

    override fun invoke(dto: AssignTaskDto) {
        val task = loadTaskPort.findById(dto.taskId)
            ?: throw IllegalArgumentException("Task [${dto.taskId}] not found")
        val user = loadUserPort.findById(dto.userId)
            ?: throw IllegalArgumentException("User [${dto.userId}] not found")

        task.assignee = user
        updateTaskPort.update(task)

        eventPublisher.publish(AssignTaskEvent(task.id, user.id))
    }
}
