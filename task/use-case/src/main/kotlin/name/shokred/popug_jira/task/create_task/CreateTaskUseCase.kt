package name.shokred.popug_jira.task.create_task

import name.shokred.popug_jira.event.EventPublisher
import name.shokred.popug_jira.task.Task
import name.shokred.popug_jira.task.port.CreateTaskPort
import name.shokred.popug_jira.task.port.TaskIdGenerator
import java.util.*

class CreateTaskUseCase(
    private val createTaskPort: CreateTaskPort,
    private val taskIdGenerator: TaskIdGenerator,
    private val eventPublisher: EventPublisher
) {

    private val random = Random()

    fun invoke(createTaskDto: CreateTaskDto) {
        val task = Task(
            id = taskIdGenerator.generate(),
            title = createTaskDto.title,
            description = createTaskDto.description
        )

        createTaskPort.create(task)
        eventPublisher.publish(CreateTaskEvent(task.id))
    }
}
