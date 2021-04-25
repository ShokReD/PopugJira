package name.shokred.popug_jira.task.complete_task

import name.shokred.popug_jira.event.DomainEvent

data class CompleteTaskEvent(val taskId: Long) : DomainEvent()
