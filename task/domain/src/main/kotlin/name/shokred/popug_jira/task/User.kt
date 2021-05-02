package name.shokred.popug_jira.task

data class User(
    val id: Long,
    val name: String,
    val tasks: List<Task>
)
