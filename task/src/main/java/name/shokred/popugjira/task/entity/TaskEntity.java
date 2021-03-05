package name.shokred.popugjira.task.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "TASKS")
@Entity
public class TaskEntity {

    @Id
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    public void setDescription(String description) {
        setChangeDate(ZonedDateTime.now());
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        setChangeDate(ZonedDateTime.now());
        this.status = status;
    }

    @Column(name = "CHANGE_DATE")
    private ZonedDateTime changeDate;

    public boolean canChangeTo(TaskStatus statusTo) {
        return statusTo.isAvailableFrom(this.status);
    }
}
