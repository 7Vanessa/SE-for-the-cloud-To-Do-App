package com.example.taskmanagerservice;

import com.example.taskmanagerservice.dto.TaskDTO;
import com.example.taskmanagerservice.entity.Task;
import com.example.taskmanagerservice.exception.TaskNotFoundException;
import com.example.taskmanagerservice.repository.TaskRepository;
import com.example.taskmanagerservice.service.TaskManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskManagerServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskManagerService taskManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateTask() {
        TaskDTO taskDTO = new TaskDTO("Test Task", "Description", false);
        Task task = new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.isCompleted());

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskManagerService.createTask(taskDTO);

        assertNotNull(createdTask);
        assertEquals(taskDTO.getTitle(), createdTask.getTitle());
        assertEquals(taskDTO.getDescription(), createdTask.getDescription());
        assertEquals(taskDTO.isCompleted(), createdTask.isCompleted());

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetTask() {
        Long taskId = 1L;
        Task task = new Task("Test Task", "Description", false);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task retrievedTask = taskManagerService.getTask(taskId);

        assertNotNull(retrievedTask);
        assertEquals(task.getTitle(), retrievedTask.getTitle());
        assertEquals(task.getDescription(), retrievedTask.getDescription());
        assertEquals(task.isCompleted(), retrievedTask.isCompleted());

        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void testGetTaskNotFound() {
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskManagerService.getTask(taskId));

        verify(taskRepository, times(1)).findById(taskId);
    }
}