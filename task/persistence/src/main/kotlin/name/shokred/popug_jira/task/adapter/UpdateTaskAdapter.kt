package name.shokred.popug_jira.task.adapter

import name.shokred.popug_jira.task.Task
import name.shokred.popug_jira.task.entity.TaskEntity
import name.shokred.popug_jira.task.port.UpdateTaskPort
import name.shokred.popug_jira.task.repository.TaskRepository

class UpdateTaskAdapter(private val taskRepository: TaskRepository) : UpdateTaskPort {

    override fun update(task: Task) {
        taskRepository.save(TaskEntity.fromDomain(task))
    }
}
