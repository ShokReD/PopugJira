package name.shokred.popugjira.task.repository;

import name.shokred.popugjira.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
