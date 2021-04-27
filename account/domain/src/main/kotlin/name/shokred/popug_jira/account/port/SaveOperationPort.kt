package name.shokred.popug_jira.account.port

import name.shokred.popug_jira.account.Operation

interface SaveOperationPort {
    fun save(operation: Operation)
}
