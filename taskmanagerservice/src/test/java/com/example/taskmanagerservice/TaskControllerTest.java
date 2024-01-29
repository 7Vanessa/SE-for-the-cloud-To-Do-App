package com.example.taskmanagerservice;

import com.example.taskmanagerservice.controller.TaskController;
import com.example.taskmanagerservice.dto.TaskDTO;
import com.example.taskmanagerservice.entity.Task;
import com.example.taskmanagerservice.service.TaskManagerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskManagerService taskManagerService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void testCreateTask() {
        TaskDTO taskDTO = new TaskDTO("Test Task", "Description", false);
        Task task = new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.isCompleted());

        when(taskManagerService.createTask(taskDTO)).thenReturn(task);

        ResponseEntity<TaskDTO> response = taskController.createTask(taskDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(taskDTO.getTitle(), response.getBody().getTitle());
        assertEquals(taskDTO.getDescription(), response.getBody().getDescription());
        assertEquals(taskDTO.isCompleted(), response.getBody().isCompleted());

        verify(taskManagerService, times(1)).createTask(taskDTO);
    }

    @Test
    void testGetTask() {
        Long taskId = 1L;
        Task task = new Task("Test Task", "Description", false);

        when(taskManagerService.getTask(taskId)).thenReturn(task);

        ResponseEntity<TaskDTO> response = taskController.getTask(taskId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task.getTitle(), response.getBody().getTitle());
        assertEquals(task.getDescription(), response.getBody().getDescription());
        assertEquals(task.isCompleted(), response.getBody().isCompleted());

        verify(taskManagerService, times(1)).getTask(taskId);
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(
                new Task("Task 1", "Description 1", false),
                new Task("Task 2", "Description 2", true)
        );

        when(taskManagerService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<TaskDTO>> response = taskController.getAllTasks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());

        verify(taskManagerService, times(1)).getAllTasks();
    }

    @Test
    void testUpdateTask() {
        Long taskId = 1L;
        TaskDTO updatedTaskDTO = new TaskDTO("Updated Task", "Updated Description", true);
        Task updatedTask = new Task(updatedTaskDTO.getTitle(), updatedTaskDTO.getDescription(), updatedTaskDTO.isCompleted());

        when(taskManagerService.updateTask(taskId, updatedTaskDTO)).thenReturn(updatedTask);

        ResponseEntity<TaskDTO> response = taskController.updateTask(taskId, updatedTaskDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTaskDTO.getTitle(), response.getBody().getTitle());
        assertEquals(updatedTaskDTO.getDescription(), response.getBody().getDescription());
        assertEquals(updatedTaskDTO.isCompleted(), response.getBody().isCompleted());

        verify(taskManagerService, times(1)).updateTask(taskId, updatedTaskDTO);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;

        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(taskManagerService, times(1)).deleteTask(taskId);
    }
}