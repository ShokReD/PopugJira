package name.shokred.popug_jira.task.port

import name.shokred.popug_jira.task.Task

interface CreateTaskPort {
    fun create(task: Task)
}
