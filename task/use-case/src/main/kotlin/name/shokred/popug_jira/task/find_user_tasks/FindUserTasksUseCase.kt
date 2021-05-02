package name.shokred.popug_jira.task.find_user_tasks

import name.shokred.popug_jira.UseCase
import name.shokred.popug_jira.task.Task
import name.shokred.popug_jira.task.port.LoadUserPort

class FindUserTasksUseCase(
    private val loadUserPort: LoadUserPort
) : UseCase<FindUserTaskDto, List<Task>> {

    override fun invoke(dto: FindUserTaskDto): List<Task> {
        val user = (loadUserPort.findById(dto.userId)
            ?: throw IllegalArgumentException("User [${dto.userId}] not found"))

        return user.tasks
    }
}
