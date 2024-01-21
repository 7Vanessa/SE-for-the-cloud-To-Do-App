package com.example.taskmanagerservice.repository;

import com.example.taskmanagerservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitle(String title);

    List<Task> findByCompleted(boolean completed);

    List<Task> findByDueDateBefore(LocalDateTime date);

    List<Task> findByTitleContainingIgnoreCase(String keyword);

    List<Task> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Task> findByCompletedAndDueDateBetween(boolean completed, LocalDateTime startDate, LocalDateTime endDate);

    List<Task> findByTitleAndCompleted(String title, boolean completed);

    List<Task> findTop5ByOrderByDueDateAsc();

    // Utilisation de l'annotation @Query pour une requête SQL personnalisée
    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword%")
    List<Task> searchTasks(@Param("keyword") String keyword);
}
