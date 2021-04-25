package name.shokred.popug_jira.task.port

import name.shokred.popug_jira.task.Task

interface LoadTaskPort {
    fun findById(taskId: Long): Task?
}
