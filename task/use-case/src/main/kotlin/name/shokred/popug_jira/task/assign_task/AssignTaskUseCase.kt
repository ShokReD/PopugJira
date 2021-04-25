package name.shokred.popug_jira.task.assign_task

import name.shokred.popug_jira.event.EventPublisher
import name.shokred.popug_jira.task.port.LoadTaskPort
import name.shokred.popug_jira.task.port.LoadUserPort
import name.shokred.popug_jira.task.port.UpdateTaskPort

class AssignTaskUseCase(
    private val loadTaskPort: LoadTaskPort,
    private val loadUserPort: LoadUserPort,
    private val updateTaskPort: UpdateTaskPort,
    private val eventPublisher: EventPublisher
) {

    fun invoke(assignTaskDto: AssignTaskDto) {
        val task = loadTaskPort.findById(assignTaskDto.taskId)
            ?: throw IllegalArgumentException("Task [${assignTaskDto.taskId}] not found")
        val user = loadUserPort.findById(assignTaskDto.userId)
            ?: throw IllegalArgumentException("User [${assignTaskDto.userId}] not found")

        task.assignee = user
        updateTaskPort.update(task)

        eventPublisher.publish(AssignTaskEvent(task.id, user.id))
    }
}
