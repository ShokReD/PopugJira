package name.shokred.popugjira.task.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TaskStatusTest {

    @ParameterizedTest
    @CsvSource({"PLANNED,REGISTERED", "DONE,REGISTERED", "DONE,PLANNED", "PLANNED_AGAIN,DONE"})
    void checkAvailableTransition(TaskStatus statusFrom, TaskStatus statusTo) {
        Assertions.assertTrue(statusFrom.isAvailableFrom(statusTo));
    }

    @ParameterizedTest
    @CsvSource({"REGISTERED,REGISTERED", "REGISTERED,PLANNED", "REGISTERED,DONE", "REGISTERED,PLANNED_AGAIN",
            "PLANNED,PLANNED", "PLANNED,DONE", "PLANNED,PLANNED_AGAIN",
            "DONE,DONE", "DONE,PLANNED_AGAIN",
            "PLANNED_AGAIN,PLANNED_AGAIN", "PLANNED_AGAIN,REGISTERED", "PLANNED_AGAIN,PLANNED"})
    void checkNotAvailableTransition(TaskStatus statusFrom, TaskStatus statusTo) {
        Assertions.assertFalse(statusFrom.isAvailableFrom(statusTo));
    }
}