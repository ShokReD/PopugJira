package name.shokred.popug_jira.task.port

import name.shokred.popug_jira.task.Task

interface UpdateTaskPort {
    fun update(task: Task)
}
