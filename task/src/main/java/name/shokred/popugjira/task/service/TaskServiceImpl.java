package name.shokred.popugjira.task.service;

import lombok.RequiredArgsConstructor;
import name.shokred.popugjira.task.entity.*;
import name.shokred.popugjira.task.error.*;
import name.shokred.popugjira.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskEntity createNewTask(String description) throws EmptyDescriptionException {
        if (description == null || description.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        TaskEntity task = TaskEntity.builder()
                .description(description)
                .status(TaskStatus.REGISTERED)
                .build();

        return taskRepository.save(task);
    }

    private TaskEntity findById(Long taskId) throws TaskNotFoundException {
        Optional<TaskEntity> entity = taskRepository.findById(taskId);
        if (!entity.isPresent()) {
            throw new TaskNotFoundException(taskId);
        }
        return entity.get();
    }

    @Override
    public TaskEntity closeTask(Long taskId) throws InvalidStateTransitionException, TaskNotFoundException {
        TaskEntity entity = findById(taskId);
        if (!entity.canChangeTo(TaskStatus.DONE)) {
            throw new InvalidStateTransitionException(entity.getStatus(), TaskStatus.DONE, taskId);
        }
        entity.setStatus(TaskStatus.DONE);

        return taskRepository.save(entity);
    }

    @Override
    public TaskEntity reopenTask(Long taskId) throws InvalidStateTransitionException, TaskNotFoundException {
        TaskEntity entity = findById(taskId);
        if (!entity.canChangeTo(TaskStatus.PLANNED_AGAIN)) {
            throw new InvalidStateTransitionException(entity.getStatus(), TaskStatus.PLANNED_AGAIN, taskId);
        }
        entity.setStatus(TaskStatus.PLANNED_AGAIN);

        return taskRepository.save(entity);
    }

    @Override
    public Collection<TaskEntity> findAll() {
        return taskRepository.findAll();
    }
}
