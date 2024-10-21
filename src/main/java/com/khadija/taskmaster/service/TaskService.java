package com.khadija.taskmaster.service;

import com.khadija.taskmaster.dto.TaskDto;
import com.khadija.taskmaster.dto.TaskMapper;
import com.khadija.taskmaster.dto.request.TaskRequest;
import com.khadija.taskmaster.exception.TaskNotFoundException;
import com.khadija.taskmaster.model.Task;
import com.khadija.taskmaster.model.User;
import com.khadija.taskmaster.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;
    private final UserService userService;


    public TaskService(TaskRepository taskRepository, TaskMapper mapper, UserService userService) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public TaskDto createTask(final TaskRequest request) {
        return mapper.fromTask(taskRepository.save(mapper.toTask(request, getCurrentUser())));
    }

    public TaskDto getTaskById(final Long id) {
        return mapper.fromTask(findTaskById(id));
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAllByUser(getCurrentUser())
                .stream()
                .map(mapper::fromTask)
                .toList();
    }

    @Transactional
    public TaskDto updateTask(final Long id, final TaskRequest request) {
        Task task = findTaskById(id);
        Task updatedTask = new Task(
                task.getId(),
                Optional.ofNullable(request.title()).orElse(task.getTitle()),
                Optional.ofNullable(request.description()).orElse(task.getDescription()),
                task.getTaskStatus(),
                task.getCreatedDate(),
                Optional.ofNullable(request.dueDate()).orElse(task.getDueDate()),
                task.getUser(),
                task.getComments()
        );
        return mapper.fromTask(taskRepository.save(updatedTask));
    }

    public void deleteTask(final Long id) {
        findTaskById(id);
        taskRepository.deleteById(id);
    }

    protected Task findTaskById(final Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    private User getCurrentUser() {
        return userService.getCurrentUser();
    }
}
