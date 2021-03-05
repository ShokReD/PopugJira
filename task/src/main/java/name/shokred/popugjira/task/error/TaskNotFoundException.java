package name.shokred.popugjira.task.error;

public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(Long taskId) {
        super(String.format("Task with id [%s] not found", taskId));
    }
}
