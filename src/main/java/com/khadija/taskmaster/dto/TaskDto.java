package com.khadija.taskmaster.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskDto(
        Long id,
        String title,
        String description,
        String taskStatus,
        LocalDateTime createdDate,
        LocalDate dueDate
) {
}
