package name.shokred.popug_jira.task.repository

import name.shokred.popug_jira.task.entity.TaskEntity
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<TaskEntity, Long> {
}
