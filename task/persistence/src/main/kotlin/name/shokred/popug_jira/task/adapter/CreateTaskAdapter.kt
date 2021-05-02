package name.shokred.popug_jira.task.adapter

import name.shokred.popug_jira.task.Task
import name.shokred.popug_jira.task.entity.TaskEntity
import name.shokred.popug_jira.task.port.CreateTaskPort
import name.shokred.popug_jira.task.repository.TaskRepository

class CreateTaskAdapter(private val taskRepository: TaskRepository) : CreateTaskPort {

    override fun create(task: Task) {
        taskRepository.save(TaskEntity.fromDomain(task))
    }
}
