package name.shokred.popug_jira.task.adapter

import name.shokred.popug_jira.account.Account
import name.shokred.popug_jira.account.port.LoadAccountPort
import name.shokred.popug_jira.task.repository.AccountRepository

class LoadAccountAdapter(private val accountRepository: AccountRepository) : LoadAccountPort {

    override fun findByUserId(userId: Long): Account? {
        return accountRepository.findOneByUserId(userId)?.toDomain()
    }
}
