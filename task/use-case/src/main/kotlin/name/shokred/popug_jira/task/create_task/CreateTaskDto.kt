package name.shokred.popug_jira.task.create_task

import name.shokred.popug_jira.UseCaseDto

data class CreateTaskDto(val title: String, val description: String) : UseCaseDto
