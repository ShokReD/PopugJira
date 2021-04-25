package name.shokred.popug_jira.task.assign_task

import name.shokred.popug_jira.event.DomainEvent

data class AssignTaskEvent(val taskId: Long, val userId: Long) : DomainEvent()
