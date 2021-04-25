package name.shokred.popug_jira.task.complete_task

import name.shokred.popug_jira.event.EventPublisher
import name.shokred.popug_jira.task.port.LoadTaskPort
import name.shokred.popug_jira.task.port.UpdateTaskPort

class CompleteTaskUseCase(
    private val loadTaskPort: LoadTaskPort,
    private val updateTaskPort: UpdateTaskPort,
    private val eventPublisher: EventPublisher
) {

    fun invoke(completeTaskDto: CompleteTaskDto) {
        val task = (loadTaskPort.findById(completeTaskDto.taskId)
            ?: throw IllegalArgumentException("Task [${completeTaskDto.taskId}] not found"))

        task.complete()
        updateTaskPort.update(task)
        eventPublisher.publish(CompleteTaskEvent(task.id))
    }
}
