package name.shokred.popugjira.task.entity;

import java.util.Arrays;

public enum TaskStatus {
    REGISTERED,
    PLANNED(REGISTERED),
    DONE(REGISTERED, PLANNED),
    PLANNED_AGAIN(DONE);

    TaskStatus(TaskStatus... availableFrom) {
        this.availableFrom = availableFrom;
    }

    private final TaskStatus[] availableFrom;

    public boolean isAvailableFrom(TaskStatus statusFrom) {
        return Arrays.asList(availableFrom).contains(statusFrom);
    }
}
