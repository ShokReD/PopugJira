package name.shokred.popug_jira.account

class User(
    val id: Long,
    val name: String,
    val operations: MutableCollection<Operation>
)
