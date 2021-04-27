package name.shokred.popug_jira.account.port

import name.shokred.popug_jira.account.Operation

interface LoadOperationPort {
    fun findByTaskId(taskId: Long): List<Operation>
    fun findTodayOperation(): List<Operation>
}
