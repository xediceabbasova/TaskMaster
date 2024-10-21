package com.khadija.taskmaster.dto;

import java.time.LocalDateTime;

public record CommentDto(
        Long id,
        String content,
        LocalDateTime createdDate,
        String authorName
) {
}
