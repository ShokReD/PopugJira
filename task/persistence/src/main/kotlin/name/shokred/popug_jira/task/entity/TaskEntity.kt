package name.shokred.popug_jira.task.entity

import name.shokred.popug_jira.task.Task
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "TASKS")
class TaskEntity(
    var title: String,
    var description: String,
    var status: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var assignee: Long?
) : BaseEntity<Long>() {

    fun toDomain(): Task {
        val task = Task(id!!, title, description)
        if (status == "COMPLETED") {
            task.complete()
        }
        return task
    }

    companion object {
        fun fromDomain(task: Task): TaskEntity {
            return TaskEntity(
                task.title,
                task.description,
                if (task.isCompleted()) "COMPLETED" else "NOT_COMPLETED",
                task.assignee?.id
            )
                .apply { id = task.id }
        }
    }
}
