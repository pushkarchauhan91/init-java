package com.javatechie.repository;

import com.javatechie.entity.Task;
import com.javatechie.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByDueDateBetween(LocalDate from, LocalDate to);

    List<Task> findByDoneDateBetween(LocalDate from, LocalDate to);

    @Query("SELECT t FROM Task t WHERE (t.createdAt IS NOT NULL AND t.createdAt >= :cutoff) OR (t.updatedAt IS NOT NULL AND t.updatedAt >= :cutoff)")
    List<Task> findRecentlyChanged(@Param("cutoff") LocalDateTime cutoff);
}

