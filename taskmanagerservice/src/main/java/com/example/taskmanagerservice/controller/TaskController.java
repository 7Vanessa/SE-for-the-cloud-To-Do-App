package com.example.taskmanagerservice.controller;

import com.example.taskmanagerservice.dto.TaskDTO;
import com.example.taskmanagerservice.entity.Task;
import com.example.taskmanagerservice.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskManagerService taskManagerService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        // Convert TaskDTO to Task entity and save to the database
        Task savedTask = taskManagerService.createTask(taskDTO);

        // Convert saved Task entity back to TaskDTO and return
        TaskDTO responseDTO = convertToTaskDTO(savedTask);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        // Retrieve Task from the database by ID
        Task task = taskManagerService.getTask(taskId);

        // Convert Task entity to TaskDTO and return
        TaskDTO responseDTO = convertToTaskDTO(task);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        // Retrieve all tasks from the database
        List<Task> tasks = taskManagerService.getAllTasks();

        // Convert Task entities to TaskDTOs and return
        List<TaskDTO> responseDTOs = tasks.stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        // Update Task in the database
        Task updatedTask = taskManagerService.updateTask(taskId, taskDTO);

        // Convert updated Task entity back to TaskDTO and return
        TaskDTO responseDTO = convertToTaskDTO(updatedTask);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        // Delete Task from the database
        taskManagerService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Helper method to convert Task entity to TaskDTO
    private TaskDTO convertToTaskDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }
}