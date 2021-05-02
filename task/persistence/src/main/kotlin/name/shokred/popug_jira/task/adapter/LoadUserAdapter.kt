package name.shokred.popug_jira.task.adapter

import name.shokred.popug_jira.task.User
import name.shokred.popug_jira.task.entity.TaskEntity
import name.shokred.popug_jira.task.entity.UserEntity
import name.shokred.popug_jira.task.port.LoadUserPort
import name.shokred.popug_jira.task.repository.UserRepository

class LoadUserAdapter(private val userRepository: UserRepository) : LoadUserPort {

    override fun findById(userId: Long): User? {
        return userRepository.findById(userId)
            .map(this::mapToDomain)
            .orElse(null)
    }

    private fun mapToDomain(entity: UserEntity): User {
        return User(
            entity.id!!,
            entity.name,
            entity.tasks.map(TaskEntity::toDomain)
        )
    }
}
