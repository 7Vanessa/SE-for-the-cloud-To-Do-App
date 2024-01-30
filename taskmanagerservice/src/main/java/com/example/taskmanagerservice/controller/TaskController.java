package com.example.taskmanagerservice.controller;

import com.example.taskmanagerservice.dto.TaskDTO;
import com.example.taskmanagerservice.entity.Task;
import com.example.taskmanagerservice.service.TaskManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Task Manager", description = "Spring Boot API for managing tasks")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskManagerService taskManagerService;

    @Operation(
            summary = "Create a task",
            description = "Create a new task by providing the task title and its description. The response is a Task.",
            tags = { "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskManagerService.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        // Convert TaskDTO to Task entity and save to the database
        Task savedTask = taskManagerService.createTask(taskDTO);

        // Convert saved Task entity back to TaskDTO and return
        TaskDTO responseDTO = convertToTaskDTO(savedTask);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Create a task",
            description = "Create a new task by providing the task title and its description. The response is a Task.",
            tags = { "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskManagerService.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        // Retrieve Task from the database by ID
        Task task = taskManagerService.getTask(taskId);

        // Convert Task entity to TaskDTO and return
        TaskDTO responseDTO = convertToTaskDTO(task);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Display a list of tasks",
            description = "Display the list of all the added tasks. The response is a list of Task.",
            tags = { "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskManagerService.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
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

    @Operation(
            summary = "Update the status of a task",
            description = "Update the status of a task (completed or not) by giving its id. The response is a Task.",
            tags = { "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskManagerService.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        // Update Task in the database
        Task updatedTask = taskManagerService.updateTask(taskId, taskDTO);

        // Convert updated Task entity back to TaskDTO and return
        TaskDTO responseDTO = convertToTaskDTO(updatedTask);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a task by ID",
            description = "Delete a task from the list of tasks by giving its ID.",
            tags = { "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskManagerService.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
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