package com.example.todoappfilestorageservice.config;

import com.example.todoappfilestorageservice.repository.FileRepository;
import com.example.todoappfilestorageservice.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class FileStorageConfig implements WebMvcConfigurer {

    @Bean
    public FileService fileService(FileRepository fileRepository) {
        return new FileService(fileRepository);
    }
}

