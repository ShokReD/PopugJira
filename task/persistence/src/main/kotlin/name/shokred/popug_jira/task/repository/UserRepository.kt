package name.shokred.popug_jira.task.repository

import name.shokred.popug_jira.task.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long>
