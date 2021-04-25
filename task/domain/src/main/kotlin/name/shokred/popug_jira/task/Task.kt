package name.shokred.popug_jira.task

class Task(
        val id: Long,
        var title: String,
        var description: String,
        var assignee: User,
        val cost: Money
) {
    private var status: TaskStatus = TaskStatus.NOT_COMPLETED

    fun complete() {
        this.status = TaskStatus.COMPLETED
    }

    fun backInWork() {
        this.status = TaskStatus.NOT_COMPLETED
    }

    fun isCompleted(): Boolean {
        return this.status == TaskStatus.COMPLETED
    }

    private enum class TaskStatus {
        NOT_COMPLETED,
        COMPLETED
    }
}
