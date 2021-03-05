package name.shokred.popugjira.task.service;

import name.shokred.popugjira.task.entity.TaskEntity;
import name.shokred.popugjira.task.error.*;

import java.util.Collection;

public interface TaskService {

    TaskEntity createNewTask(String description) throws EmptyDescriptionException;

    TaskEntity closeTask(Long taskId) throws InvalidStateTransitionException, TaskNotFoundException;

    TaskEntity reopenTask(Long taskId) throws InvalidStateTransitionException, TaskNotFoundException;

    Collection<TaskEntity> findAll();
}
