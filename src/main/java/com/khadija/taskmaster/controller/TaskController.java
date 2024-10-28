package com.khadija.taskmaster.controller;

import com.khadija.taskmaster.dto.TaskDto;
import com.khadija.taskmaster.dto.request.TaskRequest;
import com.khadija.taskmaster.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/dashboard")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "pages/dashboard";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        TaskDto task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "pages/task-details";
    }

    @PostMapping("/add-task")
    public String addTask(@ModelAttribute TaskRequest request, Model model) {
        log.info("TaskRequest:" + request.toString());
        TaskDto createdTask = taskService.createTask(request);
        model.addAttribute("task", createdTask);
        model.addAttribute("message", "Task created successfully!");
        return "pages/task-row";
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<String> updateTask(@PathVariable Long id, @ModelAttribute TaskRequest request) {
        try {
            taskService.updateTask(id, request);
            return ResponseEntity.ok()
                    .header("HX-Redirect", "/dashboard")
                    .body("");
        } catch (Exception e) {
            log.error("Error updating task: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-task/{id}")
    @ResponseBody
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        log.info("Task with id: {} was deleted", id);
    }

}
