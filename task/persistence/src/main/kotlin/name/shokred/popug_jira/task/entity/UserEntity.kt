package name.shokred.popug_jira.task.entity

import javax.persistence.*

@Entity
@Table(name = "USERS")
class UserEntity(
    var name: String,
    @OneToMany(
        mappedBy = "assignee",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    var tasks: List<TaskEntity>
) : BaseEntity<Long>()
