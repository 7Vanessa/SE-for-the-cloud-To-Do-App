package com.example.taskmanagerservice.dto;

import com.example.taskmanagerservice.entity.Task;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    private Long id;

    private Long userId;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private boolean completed;

    // Constructors
    public TaskDTO() {
    }

    public TaskDTO(Long userId, String title, String description, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public TaskDTO(Long id, Long userId, String title, String description, boolean completed) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Getters and setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Conversion methods (Task to TaskDTO and vice versa)
    public static TaskDTO fromEntity(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setUserId(task.getUserId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCompleted(task.isCompleted());
        return taskDTO;
    }

    public Task toEntity() {
        Task task = new Task();
        task.setId(this.id);
        task.setUserId(this.userId);
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setCompleted(this.completed);
        return task;
    }

}
