package com.khadija.taskmaster.dto.request;

import java.time.LocalDateTime;

public record TaskRequest(
        String title,
        String description,
        LocalDateTime dueDate
) {
}
