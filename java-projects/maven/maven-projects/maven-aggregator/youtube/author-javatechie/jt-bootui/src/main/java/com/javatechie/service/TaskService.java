package com.javatechie.service;

import com.javatechie.entity.Task;
import com.javatechie.entity.TaskStatus;
import com.javatechie.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Cacheable(value = "tasks", key = "'all'", unless = "#result == null || #result.isEmpty()")
    public List<Task> getAllTasks() {
        log.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    @Cacheable(value = "tasks", key = "#id")
    public Task getTaskById(Long id) {
        log.info("Fetching task by id={}", id);
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
    }

    @CacheEvict(value = "tasks", allEntries = true)
    public Task saveTask(Task task) {
        log.info("Saving task with id={}", task.getId());
        LocalDateTime now = LocalDateTime.now();
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(now);
        }
        task.setUpdatedAt(now);
        return taskRepository.save(task);
    }

    @CacheEvict(value = "tasks", allEntries = true)
    public void deleteTask(Long id) {
        log.info("Deleting task with id={}", id);
        taskRepository.deleteById(id);
    }

    public List<Task> filterTasks(TaskStatus status, LocalDate fromDue, LocalDate toDue, LocalDate fromDone, LocalDate toDone) {
        // Simple in-memory filter for flexibility; fine for small datasets
        return taskRepository.findAll().stream()
                .filter(t -> status == null || t.getStatus() == status)
                .filter(t -> fromDue == null || (t.getDueDate() != null && !t.getDueDate().isBefore(fromDue)))
                .filter(t -> toDue == null || (t.getDueDate() != null && !t.getDueDate().isAfter(toDue)))
                .filter(t -> fromDone == null || (t.getDoneDate() != null && !t.getDoneDate().isBefore(fromDone)))
                .filter(t -> toDone == null || (t.getDoneDate() != null && !t.getDoneDate().isAfter(toDone)))
                .toList();
    }
}

