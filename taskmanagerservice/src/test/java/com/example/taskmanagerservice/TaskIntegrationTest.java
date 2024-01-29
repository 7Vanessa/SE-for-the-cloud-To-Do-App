package com.example.taskmanagerservice;

import com.example.taskmanagerservice.controller.TaskController;
import com.example.taskmanagerservice.dto.TaskDTO;
import com.example.taskmanagerservice.entity.Task;
import com.example.taskmanagerservice.service.TaskManagerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskIntegrationTest {

    @Autowired
    private TaskManagerService taskManagerService;

    @Autowired
    private TaskController taskController;

    @Test
    void testCreateTaskAndVerifyInDatabase() {

        TaskDTO taskDTO = new TaskDTO("Test Task", "Description", false);

        ResponseEntity<TaskDTO> response = taskController.createTask(taskDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Long taskId = response.getBody().getId();

        Task createdTask = taskManagerService.getTask(taskId);

        assertEquals(taskDTO.getTitle(), createdTask.getTitle());
        assertEquals(taskDTO.getDescription(), createdTask.getDescription());
        assertEquals(taskDTO.isCompleted(), createdTask.isCompleted());
    }

    @Test
    void testDeleteTask() {
        Long taskId = createTaskAndGetId();

        ResponseEntity<Void> deletionResponse = taskController.deleteTask(taskId);

        assertEquals(HttpStatus.NO_CONTENT, deletionResponse.getStatusCode());

        assertFalse(taskManagerService.taskExists(taskId), "La tâche devrait être supprimée de la base de données");
    }

    @Test
    void testUpdateTask() {
        Long taskId = createTaskAndGetId();

        TaskDTO updatedTaskDTO = new TaskDTO("Updated Task", "Updated Description", true);

        ResponseEntity<TaskDTO> updateResponse = taskController.updateTask(taskId, updatedTaskDTO);

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());

        Task updatedTask = taskManagerService.getTask(taskId);

        assertEquals(updatedTaskDTO.getTitle(), updatedTask.getTitle());
        assertEquals(updatedTaskDTO.getDescription(), updatedTask.getDescription());
        assertEquals(updatedTaskDTO.isCompleted(), updatedTask.isCompleted());
    }

    private Long createTaskAndGetId() {
        TaskDTO taskDTO = new TaskDTO("Test Task", "Description", false);

        ResponseEntity<TaskDTO> response = taskController.createTask(taskDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody().getId();
    }
}