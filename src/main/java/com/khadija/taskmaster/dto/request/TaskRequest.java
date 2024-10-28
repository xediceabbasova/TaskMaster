package com.khadija.taskmaster.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record TaskRequest(
        String title,
        String description,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dueDate
) {
}
