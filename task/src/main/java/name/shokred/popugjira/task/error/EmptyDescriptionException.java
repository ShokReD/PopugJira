package name.shokred.popugjira.task.error;

public class EmptyDescriptionException extends TaskException {

    public EmptyDescriptionException() {
        super("Empty description for task is deny");
    }
}
