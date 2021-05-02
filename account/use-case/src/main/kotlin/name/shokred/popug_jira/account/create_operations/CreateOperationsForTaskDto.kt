package name.shokred.popug_jira.account.create_operations

import name.shokred.popug_jira.UseCaseDto

data class CreateOperationsForTaskDto(val userId: Long, val taskId: Long) : UseCaseDto
