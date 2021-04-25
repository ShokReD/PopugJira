package name.shokred.popug_jira.account.port

import name.shokred.popug_jira.account.Account

interface LoadAccountPort {
    fun findByUserId(userId: Long): Account?
}
