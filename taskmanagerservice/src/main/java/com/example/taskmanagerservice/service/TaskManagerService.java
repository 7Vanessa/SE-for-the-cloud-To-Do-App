package com.example.taskmanagerservice.service;

import com.example.taskmanagerservice.dto.TaskDTO;
import com.example.taskmanagerservice.entity.Task;
import com.example.taskmanagerservice.exception.TaskNotFoundException;
import com.example.taskmanagerservice.repository.TaskRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskManagerService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskManagerService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Long extractUserIdFromToken(String jwtToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey("${todo.app.jwtSecret}").build().parseClaimsJws(jwtToken).getBody();
        return claims.get("userId", Long.class);
    }

    public Task createTask(TaskDTO taskDTO, String jwtToken) {
        //Extract the user ID from the JWT token
        Long userId = extractUserIdFromToken(jwtToken);

        //Use the user ID when creating the task
        taskDTO.setUserId(userId);

        // Convert TaskDTO to Task entity
        Task task = taskDTO.toEntity();

        // Save the task to the database
        return taskRepository.save(task);
    }

    public Task getTask(Long taskId) {
        // Retrieve task from the database by ID
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));
    }

    public List<Task> getAllTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public List<Task> getAllTasks() {
        // Retrieve all tasks from the database
        return taskRepository.findAll();
    }

    public Task updateTask(Long taskId, TaskDTO taskDTO) {
        // Retrieve task from the database by ID
        Task existingTask = getTask(taskId);

        // Update task properties from TaskDTO
        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setCompleted(taskDTO.isCompleted());

        // Save the updated task to the database
        return taskRepository.save(existingTask);
    }

    public boolean taskExists(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return task.isPresent();
    }

    public void deleteTask(Long taskId) {
        // Retrieve task from the database by ID
        Task task = getTask(taskId);

        // Delete the task from the database
        taskRepository.delete(task);
    }
}
