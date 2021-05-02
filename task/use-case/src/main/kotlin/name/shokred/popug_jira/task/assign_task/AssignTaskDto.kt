package name.shokred.popug_jira.task.assign_task

import name.shokred.popug_jira.UseCaseDto

data class AssignTaskDto(val taskId: Long, val userId: Long) : UseCaseDto
