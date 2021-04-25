package name.shokred.popug_jira.account.port

import name.shokred.popug_jira.account.Account

interface SaveAccountPort {
    fun save(account: Account)
}
