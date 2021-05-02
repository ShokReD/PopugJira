package name.shokred.popug_jira.task.adapter

import name.shokred.popug_jira.task.Task
import name.shokred.popug_jira.task.entity.TaskEntity
import name.shokred.popug_jira.task.port.LoadTaskPort
import name.shokred.popug_jira.task.repository.TaskRepository

class LoadTaskAdapter(
    private val taskRepository: TaskRepository,
    private val loadUserAdapter: LoadUserAdapter
) : LoadTaskPort {

    override fun findById(taskId: Long): Task? {
        return taskRepository.findById(taskId)
            .map(this::mapToDomain)
            .orElse(null)
    }

    private fun mapToDomain(entity: TaskEntity): Task {
        return Task(entity.id!!, entity.title, entity.description)
            .apply {
                if (entity.assignee != null) {
                    this.assignee = loadUserAdapter.findById(entity.assignee!!)
                }
            }
    }
}
