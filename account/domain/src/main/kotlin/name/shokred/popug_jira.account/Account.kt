package name.shokred.popug_jira.account

class Account(
    val id: Long,
    val userId: Long,
    val operations: MutableCollection<Operation>
)
