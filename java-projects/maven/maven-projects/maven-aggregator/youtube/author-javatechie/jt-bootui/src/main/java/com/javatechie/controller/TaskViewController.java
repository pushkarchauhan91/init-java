package com.javatechie.controller;

import com.javatechie.entity.Task;
import com.javatechie.entity.TaskStatus;
import com.javatechie.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ui/tasks")
@Slf4j
@RequiredArgsConstructor
public class TaskViewController {

    private final TaskService taskService;

    @GetMapping
    public String listTasks(@RequestParam(value = "status", required = false) TaskStatus status,
                            @RequestParam(value = "fromDue", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDue,
                            @RequestParam(value = "toDue", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDue,
                            @RequestParam(value = "fromDone", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDone,
                            @RequestParam(value = "toDone", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDone,
                            Model model) {
        log.info("Rendering task list UI with filters: status={}, fromDue={}, toDue={}, fromDone={}, toDone={}",
                status, fromDue, toDue, fromDone, toDone);

        List<Task> tasks = taskService.filterTasks(status, fromDue, toDue, fromDone, toDone);

        model.addAttribute("tasks", tasks);
        model.addAttribute("statuses", Arrays.asList(TaskStatus.values()));
        model.addAttribute("selectedStatus", status);
        model.addAttribute("fromDue", fromDue);
        model.addAttribute("toDue", toDue);
        model.addAttribute("fromDone", fromDone);
        model.addAttribute("toDone", toDone);
        model.addAttribute("pageTitle", "Task Manager");
        return "tasks";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        log.info("Rendering create task form");
        model.addAttribute("task", new Task());
        model.addAttribute("statuses", Arrays.asList(TaskStatus.values()));
        model.addAttribute("pageTitle", "Create Task");
        return "task-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        log.info("Rendering edit form for task id={}", id);
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("statuses", Arrays.asList(TaskStatus.values()));
        model.addAttribute("pageTitle", "Edit Task");
        return "task-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") Task task) {
        log.info("Processing save for task id={}", task.getId());
        taskService.saveTask(task);
        return "redirect:/ui/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        log.info("Processing delete for task id={}", id);
        taskService.deleteTask(id);
        return "redirect:/ui/tasks";
    }
}

