package name.shokred.popug_jira.task.create_task

import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.event.EventPublisher
import name.shokred.popug_jira.task.Task
import name.shokred.popug_jira.task.port.CreateTaskPort
import name.shokred.popug_jira.task.port.TaskIdGenerator
import java.util.*

class CreateTaskUseCase(
    private val createTaskPort: CreateTaskPort,
    private val taskIdGenerator: TaskIdGenerator,
    private val eventPublisher: EventPublisher
) : UseCase<CreateTaskDto> {

    private val random = Random()

    override fun invoke(dto: CreateTaskDto) {
        val task = Task(
            id = taskIdGenerator.generate(),
            title = dto.title,
            description = dto.description
        )

        createTaskPort.create(task)
        eventPublisher.publish(CreateTaskEvent(task.id))
    }
}
