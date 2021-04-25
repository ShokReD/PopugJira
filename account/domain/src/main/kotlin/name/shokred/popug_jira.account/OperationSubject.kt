package name.shokred.popug_jira.account

interface OperationSubject {
    fun subjectId(): Long
    fun subjectType(): String
}
