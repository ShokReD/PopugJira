package name.shokred.popug_jira.task.port

import name.shokred.popug_jira.task.User

interface LoadUserPort {
    fun findById(userId: Long): User?
}
