package name.shokred.popugjira.task.error;

import name.shokred.popugjira.task.entity.TaskStatus;

public class InvalidStateTransitionException extends TaskException {

    public InvalidStateTransitionException(TaskStatus from, TaskStatus to, Long taskId) {
        super(String.format("Unable to change status from [%s] to [%s] for task [%d]", from, to, taskId));
    }
}
