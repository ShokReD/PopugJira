package name.shokred.popug_jira.account

data class Task(val id: Long) : OperationSubject {
    override fun subjectId(): Long {
        return id
    }

    override fun subjectType(): String {
        return "task"
    }
}
