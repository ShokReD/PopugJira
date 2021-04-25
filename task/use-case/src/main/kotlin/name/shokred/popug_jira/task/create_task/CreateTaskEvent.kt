package name.shokred.popug_jira.task.create_task

import name.shokred.popug_jira.event.DomainEvent

data class CreateTaskEvent(val taskId: Long) : DomainEvent()
