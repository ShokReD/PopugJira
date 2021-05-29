package name.shokred.popug_jira.task.entity

import name.shokred.popug_jira.account.OperationSubject
import name.shokred.popug_jira.account.Task
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "OPERATION_SUBJECTS")
class OperationSubjectEntity(
    var subjectId: Long,
    var subjectType: String
) : BaseEntity<Long>() {

    fun toDomain(): OperationSubject {
        return when (subjectType) {
            "task" -> Task(subjectId)
            else -> throw IllegalStateException("Unknown subject type")
        }
    }

    companion object {
        fun fromDomain(subject: OperationSubject): OperationSubjectEntity {
            return OperationSubjectEntity(subject.subjectId(), subject.subjectType())
        }
    }
}
