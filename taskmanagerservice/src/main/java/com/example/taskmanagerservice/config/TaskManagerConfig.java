package com.example.taskmanagerservice.config;

import com.example.taskmanagerservice.service.TaskManagerService;
import com.example.taskmanagerservice.repository.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class TaskManagerConfig implements WebMvcConfigurer {

    @Bean
    public TaskManagerService taskManagerService(TaskRepository taskRepository) {
        return new TaskManagerService(taskRepository);
    }
}

