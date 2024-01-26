package com.example.taskmanagerservice.repository;

import com.example.taskmanagerservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitle(String title);

    List<Task> findByCompleted(boolean completed);


    List<Task> findByTitleContainingIgnoreCase(String keyword);


    List<Task> findByTitleAndCompleted(String title, boolean completed);

    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword%")
    List<Task> searchTasks(@Param("keyword") String keyword);
}
