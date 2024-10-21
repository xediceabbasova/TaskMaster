package com.khadija.taskmaster.dto;

import com.khadija.taskmaster.dto.request.TaskRequest;
import com.khadija.taskmaster.model.Task;
import com.khadija.taskmaster.model.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toTask(TaskRequest taskRequest, User user) {
        return new Task(
                taskRequest.title(),
                taskRequest.description(),
                taskRequest.dueDate(),
                user
        );
    }

    public TaskDto fromTask(Task from) {
        return new TaskDto(
                from.getId(),
                from.getTitle(),
                from.getDescription(),
                from.getTaskStatus().name(),
                from.getCreatedDate(),
                from.getDueDate()
        );
    }
}
